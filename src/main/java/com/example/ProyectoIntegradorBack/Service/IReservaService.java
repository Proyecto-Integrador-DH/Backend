package com.example.ProyectoIntegradorBack.Service;

import com.example.ProyectoIntegradorBack.Model.DTOs.ReservaDTO;

import java.util.List;

public interface IReservaService {
    ReservaDTO postReserva(ReservaDTO reservaDTO);
    ReservaDTO getReservaById(Integer id);
    ReservaDTO updateReserva(ReservaDTO reservaDTO);
    List<ReservaDTO> allReservas();
}
