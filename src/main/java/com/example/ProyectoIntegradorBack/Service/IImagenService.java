package com.example.ProyectoIntegradorBack.Service;

import com.example.ProyectoIntegradorBack.Model.Imagen;

import java.util.List;

public interface IImagenService {

    void guardarImagen(Imagen imagen);

    List<Imagen> getImagenes(Integer productoId);
}
