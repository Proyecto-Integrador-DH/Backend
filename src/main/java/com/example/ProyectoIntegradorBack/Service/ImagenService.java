package com.example.ProyectoIntegradorBack.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.ProyectoIntegradorBack.Model.Imagen;
import com.example.ProyectoIntegradorBack.Model.Producto;
import com.example.ProyectoIntegradorBack.Repository.IImagenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImagenService {

    @Autowired
    private IImagenRepository imagenRepository;

    @Autowired
    private ProductoService productoService;

    private final AmazonS3 s3 =  (AmazonS3Client) AmazonS3ClientBuilder.standard().build();
    private final String bucketName = "test-s3-java-upload";
    public void guardarImagen(Imagen imagen){
        Integer productoId = imagen.getProducto().getId();
        Producto producto = productoService.getProducto(productoId);

        List<String> base64Images = imagen.getImgPath() != null ? imagen.getImgPath() : Collections.emptyList();
        String altText = imagen.getAltText() != null ? imagen.getAltText() : "img";
        RandomLetras randomLetras = new RandomLetras();
        String path = randomLetras.randomString(4);

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

    public class RandomLetras {
        public String randomString(int length) {
            String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                int index = (int)(letras.length() * Math.random());
                sb.append(letras.charAt(index));
            }
            return sb.toString();
        }
    }

    public List<Imagen> getImagenes(Integer productoId){
        return imagenRepository.findByProductoId(productoId);
    }
}
