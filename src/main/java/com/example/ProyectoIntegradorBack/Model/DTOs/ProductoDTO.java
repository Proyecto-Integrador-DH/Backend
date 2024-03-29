package com.example.ProyectoIntegradorBack.Model.DTOs;

import com.example.ProyectoIntegradorBack.Model.Imagen;

import java.util.List;

public record ProductoDTO(Integer Id, String nombre, String descripcion, boolean disponible, CategoriaDTO categoria, List<ImagenDTO> imagenes){
    public ProductoDTO(Integer id, String nombre, String descripcion, boolean disponible, List<Imagen> imagenes) {
        this(id, nombre, descripcion, disponible, null, null);
    }
}
