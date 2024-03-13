package com.example.ProyectoIntegradorBack.Model.DTOs;

public record FavoritoDTO(Integer id, ClienteDTO cliente, ProductoDTO producto, Boolean estado) {
    public FavoritoDTO {
        if (estado == null) {
            estado = true;
        }
    }

    public FavoritoDTO withEstado(Boolean nuevoEstado) {
        return new FavoritoDTO(id, cliente, producto, nuevoEstado);
    }
}
