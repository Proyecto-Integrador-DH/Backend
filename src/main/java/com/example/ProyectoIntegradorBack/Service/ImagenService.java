package com.example.ProyectoIntegradorBack.Service;

import com.example.ProyectoIntegradorBack.Model.Imagen;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImagenService {

    @Autowired
    private ImagenCarga imagenCarga;
    public void guardarImagen(Imagen imagen){
        imagenCarga.cargarImagen(imagen);
    }

}
