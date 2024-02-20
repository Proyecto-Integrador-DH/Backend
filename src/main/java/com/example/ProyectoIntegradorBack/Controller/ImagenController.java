package com.example.ProyectoIntegradorBack.Controller;

import com.example.ProyectoIntegradorBack.Model.Imagen;
import com.example.ProyectoIntegradorBack.Service.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/imagen")
public class ImagenController {

    @Autowired
    private ImagenService imagenService;

    @PostMapping("/cargar")
    public void cargarImagen(@RequestBody Integer productoId, Imagen imagen){
        imagenService.guardarImagen(productoId, imagen);
    }

    @GetMapping("/listar/{productoId}")
    public void listarImagenes(@PathVariable Integer productoId){
        imagenService.getImagenes(productoId);
    }
}
