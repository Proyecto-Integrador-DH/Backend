package com.example.ProyectoIntegradorBack.Model.DTOs;

public record CategoriaNombreDTO(Integer id, String nombre) {
    public String getNombre() {
        return nombre;
    }

    public Integer getId() {
        return id;
    }
}
