package com.example.ProyectoIntegradorBack.Model.DTOs;

import java.util.List;

public record CategoriaDTO(String nombre, List<ProductoDTOCat> productos) {
}
