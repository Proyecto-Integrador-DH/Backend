package com.example.ProyectoIntegradorBack.Model.DTOs;

public record ReservaDTO(Integer id, AgendaDTO agenda, ClienteDTO cliente, Integer cantidad, Boolean estado) {
    public ReservaDTO withAgenda(AgendaDTO agendaDTO) {
        return new ReservaDTO(id, agendaDTO, cliente, cantidad, estado);
    }
}
