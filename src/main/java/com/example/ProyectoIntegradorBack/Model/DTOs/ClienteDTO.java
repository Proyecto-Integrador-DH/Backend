package com.example.ProyectoIntegradorBack.Model.DTOs;

import java.util.List;

public record ClienteDTO(Integer id, Integer usuario_id, String tipoDocumento, String numeroDocumento, String telefono, String direccion, String ciudad, String pais, List<ReservaDTO> reservas){
    public Integer usuarioId() {
        return usuario_id;
    }
}
