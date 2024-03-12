package com.example.ProyectoIntegradorBack.Service.Impl;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.example.ProyectoIntegradorBack.Model.Icono;
import com.example.ProyectoIntegradorBack.Repository.IIconoRepository;
import com.example.ProyectoIntegradorBack.Service.IIconoService;
import com.example.ProyectoIntegradorBack.Utils.RandomLetras;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service
@RequiredArgsConstructor
public class IconoService implements IIconoService {
    @Autowired
    private IIconoRepository iconoRepository;
    private final AmazonS3 s3 = (AmazonS3Client) AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
    private final String bucketName = "iconosdh";

    @Override
    public void guardarIcono(Icono icono) {

        String base64Images = icono.getIconPath() != null ? icono.getIconPath() : null;
        String altText = icono.getAlt() != null ? icono.getAlt() : "img";
        String path = RandomLetras.randomString(4);
        System.out.println("Path: " + path);

        String base64 = base64Images;
        String keyName = altText + ".png";//path + "/" + altText + i + ".png";

        byte[] decodedBytes = Base64.getDecoder().decode(base64);

        File file = new File("imagen.png");
        try (OutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(decodedBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Icono iconoNuevo = new Icono();
        Integer idIcono = icono.getId();

        if(idIcono != null){
            iconoNuevo = iconoRepository.findById(idIcono).get();

            try{
                eliminarIcono(idIcono, false);
            }catch (Exception e){

            }
        }

        // subir la imagen
        PutObjectResult result = s3.putObject(new PutObjectRequest(bucketName, keyName, file));

        String publicUrl = s3.getUrl(bucketName, keyName).toString();
        System.out.println("URL pública de la imagen: " + publicUrl);

        iconoNuevo.setAlt(altText);
        iconoNuevo.setNombre(icono.getNombre());
        iconoNuevo.setUrl(publicUrl);

        iconoRepository.save(iconoNuevo);

        System.out.println("Icono cargada exitosamente a S3.");

    }

    public void eliminarIcono(Integer id, boolean eliminarRegistro) throws Exception {
        if (iconoRepository.existsById(id))
        {
            Optional<Icono> icono = iconoRepository.findById(id);

            String imageKey = extraerNombreArchivo(icono.get().getUrl());
            System.out.println("la imagen extraida es " +imageKey);

            s3.deleteObject(new DeleteObjectRequest(bucketName, imageKey));
            System.out.println("Imagen eliminada con éxito de S3.");

            if (eliminarRegistro)
                iconoRepository.deleteById(id);
        } else {
            throw new Exception("El icono con el ID " + id + " no existe.");
        }

    }

    public String extraerNombreArchivo(String url) {
        // Expresión regular para encontrar el nombre del archivo en la URL
        String regex = "[^/]+\\.[a-zA-Z0-9]{1,5}$"; // Busca una secuencia de caracteres seguida de un punto y una extensión de hasta 5 caracteres al final de la URL
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);

        if (matcher.find()) {
            return matcher.group(); // Devuelve la coincidencia encontrada
        }

        return null; // Si no se encuentra coincidencia, devuelve null
    }

    public List<Icono> getIconos(){
        return iconoRepository.findAll();
    }
}
