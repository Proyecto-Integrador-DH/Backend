package com.example.ProyectoIntegradorBack.Controller;

import com.example.ProyectoIntegradorBack.Model.Imagen;
import com.example.ProyectoIntegradorBack.Service.Impl.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/imagen")
public class ImagenController {

    @Autowired
    private ImagenService imagenService;

    @PostMapping("/cargar")
    public ResponseEntity<?> cargarImagen(@RequestBody Imagen imagen){
        try{
            imagenService.guardarImagen(imagen);
            return ResponseEntity.ok("Imagenes guardadas con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Hubo un error al procesar la solicitud.");
        }
    }

    @GetMapping("/listar/{productoId}")
    public ResponseEntity<?> listarImagenes(@PathVariable Integer productoId){
        try{
            return ResponseEntity.ok(imagenService.getImagenes(productoId));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Hubo un error al procesar la solicitud.");
        }
    }
}
