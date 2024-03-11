package com.example.ProyectoIntegradorBack.Model.DTOs;

import java.util.Date;
import java.util.List;

public record AgendaDTO(Integer id, ProductoDTO producto, Integer cupos, Date fecha, Boolean estado){
    public AgendaDTO withProducto(ProductoDTO productoDTO) {
        return new AgendaDTO(id, productoDTO, cupos, fecha, estado);
    }
}
