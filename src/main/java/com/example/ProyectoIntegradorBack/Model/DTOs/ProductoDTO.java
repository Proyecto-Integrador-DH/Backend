package com.example.ProyectoIntegradorBack.Model.DTOs;

import com.example.ProyectoIntegradorBack.Model.Categoria;

import java.util.Date;
import java.util.List;

public record ProductoDTO(Integer Id, String nombre, String descripcion, boolean disponible, CategoriaDTO categoria, List<ImagenDTO> imagenes){
    public ProductoDTO(Integer id, String nombre, String descripcion, boolean disponible) {
        this(id, nombre, descripcion, disponible, null, null);
    }
}
