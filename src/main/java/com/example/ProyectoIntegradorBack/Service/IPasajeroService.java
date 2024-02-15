package com.example.ProyectoIntegradorBack.Service;

import com.example.ProyectoIntegradorBack.Model.PasajeroDTO;

import java.util.Set;

public interface IPasajeroService {
    void registrarPasajero(PasajeroDTO pasajeroDTO);
    PasajeroDTO verPasajero(Integer id);
    void modificarPasajero(PasajeroDTO pasajeroDTO);
    void eliminarPasajero(Integer id);
    Set<PasajeroDTO>listarTodos();
}
