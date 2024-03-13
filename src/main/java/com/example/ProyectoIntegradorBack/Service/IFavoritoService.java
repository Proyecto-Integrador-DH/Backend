package com.example.ProyectoIntegradorBack.Service;

import com.example.ProyectoIntegradorBack.Model.DTOs.FavoritoDTO;

public interface IFavoritoService {
    FavoritoDTO save(FavoritoDTO favoritoDTO);
    FavoritoDTO update(FavoritoDTO favoritoDTO);
}
