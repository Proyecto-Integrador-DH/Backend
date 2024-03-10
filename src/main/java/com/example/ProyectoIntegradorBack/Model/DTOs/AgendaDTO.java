package com.example.ProyectoIntegradorBack.Model.DTOs;

import java.util.Date;
import java.util.List;

public record AgendaDTO(ProductoDTO producto, Integer cupos, Date fecha, Boolean estado){
    public AgendaDTO withProducto(ProductoDTO productoDTO) {
        return new AgendaDTO(productoDTO, cupos, fecha, estado);
    }
}
