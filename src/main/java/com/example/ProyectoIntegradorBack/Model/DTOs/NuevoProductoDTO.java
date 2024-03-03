package com.example.ProyectoIntegradorBack.Model.DTOs;

import com.example.ProyectoIntegradorBack.Model.Categoria;

import java.util.Date;
import java.util.List;

public record NuevoProductoDTO(String nombre, String descripcion, Date fecha, int cupo, boolean disponible, Categoria categoria, List<ImagenDTO> imagenes) {
}
