package com.example.ProyectoIntegradorBack.Service;

import com.example.ProyectoIntegradorBack.Model.DTOs.ClienteDTO;

import java.util.List;

public interface IClienteService {
    public ClienteDTO save(ClienteDTO cliente);
    public ClienteDTO findById(Integer id);
    public ClienteDTO update(ClienteDTO cliente);
    public List<ClienteDTO> findAll();

    public ClienteDTO findByUsuarioId(Integer id);
}
