package com.example.ProyectoIntegradorBack.Utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Service
public class ClaveService {
    @Value("${usuario.microservicio.url}")
    private String usuarioMicroservicioUrl;
    private RestTemplate restTemplate = new RestTemplate();

    public PublicKey convertirClavePublica(String publicKeyBase64) {
        try {
            byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyBase64);
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(publicKeyBytes));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public PublicKey obtenerClavePublica() {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(usuarioMicroservicioUrl + "/clave", String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                String publicKeyBase64 = response.getBody();
                return convertirClavePublica(publicKeyBase64);
            } else {
                return null;
            }
        } catch (HttpStatusCodeException e) {
            e.printStackTrace();
            return null;
        }
    }
}
