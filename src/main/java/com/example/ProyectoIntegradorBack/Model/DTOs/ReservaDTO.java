package com.example.ProyectoIntegradorBack.Model.DTOs;

public record ReservaDTO(Integer id, AgendaDTO agenda, ClienteDTO cliente, Integer cantidad, Boolean estado) {
}
