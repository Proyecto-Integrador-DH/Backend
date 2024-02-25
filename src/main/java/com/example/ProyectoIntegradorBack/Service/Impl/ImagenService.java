package com.example.ProyectoIntegradorBack.Service.Impl;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.ProyectoIntegradorBack.Model.Imagen;
import com.example.ProyectoIntegradorBack.Model.Producto;
import com.example.ProyectoIntegradorBack.Repository.IImagenRepository;
import com.example.ProyectoIntegradorBack.Service.IImagenService;
import com.example.ProyectoIntegradorBack.Utils.RandomLetras;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ImagenService implements IImagenService {

    @Autowired
    private IImagenRepository imagenRepository;

    @Autowired
    private ProductoService productoService;

    private final AmazonS3 s3 =  (AmazonS3Client) AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
    private final String bucketName = "test-s3-java-upload";

    @Override
    public void guardarImagen(Imagen imagen){
        System.out.println("Imagen del backend service: " + imagen.toString());
        Integer productoId = imagen.getProducto().getId();
        Producto producto = productoService.getProducto(productoId);

        List<String> base64Images = imagen.getImgPath() != null ? imagen.getImgPath() : Collections.emptyList();
        String altText = imagen.getAltText() != null ? imagen.getAltText() : "img";
        String path = RandomLetras.randomString(4);
        System.out.println("Path: " + path);

        for(int i = 0; i < base64Images.size(); i++){
            String base64 = base64Images.get(i);
            String keyName = path + "/" + altText + i + ".png";

            byte[] decodedBytes = Base64.getDecoder().decode(base64);

            File file = new File("imagen" + i + ".png");
            try (OutputStream outputStream = new FileOutputStream(file)) {
                outputStream.write(decodedBytes);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Imagen imagenNueva = new Imagen();
            imagenNueva.setAltText(altText);
            imagenNueva.setUrl("https://test-s3-java-upload.s3.amazonaws.com/" + keyName);
            imagenNueva.setProducto(producto);
            imagenRepository.save(imagenNueva);

            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, keyName, file);
            s3.putObject(putObjectRequest);

            System.out.println("Imagen cargada exitosamente a S3.");
        }
    }



    @Override
    public List<Imagen> getImagenes(Integer productoId){
        return imagenRepository.findByProductoId(productoId);
    }
}
