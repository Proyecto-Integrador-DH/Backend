package com.example.ProyectoIntegradorBack.Model.DTOs;

import com.example.ProyectoIntegradorBack.Model.Categoria;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public record NuevoProductoDTO(String nombre, String descripcion, boolean disponible, String ubicacion, Categoria categoria, List<ImagenDTO> imagenes, List<Integer> caracteristicas) {
    public String getNombre() {
        return nombre;
    }
}
