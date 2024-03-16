package com.example.ProyectoIntegradorBack.Model.DTOs;

public record FavoritoDTO(Integer id, ClienteDTO cliente, ProductoDTO producto) {
    public FavoritoDTO withProducto(ProductoDTO productoDTO) {
        return new FavoritoDTO(id, cliente, productoDTO);
    }
}
