package com.example.ProyectoIntegradorBack.Service;

import com.example.ProyectoIntegradorBack.Model.DTOs.FavoritoDTO;

public interface IFavoritoService {
    FavoritoDTO save(FavoritoDTO favoritoDTO);
    void delete(FavoritoDTO favoritoDTO);
    FavoritoDTO favoritoByIdClient(Integer clienteId, Integer productoId);
}
