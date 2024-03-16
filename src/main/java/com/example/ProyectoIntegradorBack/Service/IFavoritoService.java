package com.example.ProyectoIntegradorBack.Service;

import com.example.ProyectoIntegradorBack.Model.DTOs.FavoritoDTO;

import java.util.List;

public interface IFavoritoService {
    FavoritoDTO save(FavoritoDTO favoritoDTO);
    void delete(FavoritoDTO favoritoDTO);
    FavoritoDTO favoritoByIdClient(Integer clienteId, Integer productoId);
    List<FavoritoDTO> favoritosByClient(Integer clienteId);
}
