package com.example.ProyectoIntegradorBack.Model.DTOs;

public record ReservaDTO(Integer id, AgendaDTO agenda, Integer cantidad, Boolean estado) {
}
