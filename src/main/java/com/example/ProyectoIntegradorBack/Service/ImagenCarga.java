package com.example.ProyectoIntegradorBack.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.ProyectoIntegradorBack.Model.Imagen;
import com.example.ProyectoIntegradorBack.Model.ImagenDTO;
import com.example.ProyectoIntegradorBack.Repository.IImagenRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImagenCarga {

    @Autowired
    private IImagenRepository imagenRepository;

    @Autowired
    ObjectMapper mapper;

    private final AmazonS3 s3 =  (AmazonS3Client) AmazonS3ClientBuilder.standard().build();
    private final String bucketName = "test-s3-java-upload";

    public void cargarImagen(Imagen imagen){
        List<String> base64Images = imagen.getImgPath() != null ? imagen.getImgPath() : Collections.emptyList();
        String altText = imagen.getAltText();

        for(int i = 0; i < base64Images.size(); i++){
            String base64 = base64Images.get(i);
            String altTextImg = altText + "img" != null ? altText + i : "Imagen " + i;

            byte[] decodedBytes = Base64.getDecoder().decode(base64);

            File file = new File("imagen" + i + ".png");
            try (OutputStream outputStream = new FileOutputStream(file)) {
                outputStream.write(decodedBytes);
            } catch (IOException e) {
                e.printStackTrace();
            }

            imagenRepository.save(imagen);
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, altTextImg, file);
            s3.putObject(putObjectRequest);

            System.out.println("Imagen cargada exitosamente a S3.");
        }
    }
}
