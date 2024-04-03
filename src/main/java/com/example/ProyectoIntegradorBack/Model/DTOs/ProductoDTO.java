package com.example.ProyectoIntegradorBack.Model.DTOs;

import com.example.ProyectoIntegradorBack.Model.Imagen;

import java.util.ArrayList;
import java.util.List;

public record ProductoDTO(Integer Id, String nombre, String descripcion, boolean disponible, String ubicacion, CategoriaDTO categoria, List<ImagenDTO> imagenes, List<Integer> caracteristicas){
    public ProductoDTO(Integer id, String nombre, String descripcion, boolean disponible, String ubicacion, List<Imagen> imagenes, ArrayList<Integer> caracteristicas) {
        this(id, nombre, descripcion, disponible, ubicacion,null, null, caracteristicas);
    }
}
