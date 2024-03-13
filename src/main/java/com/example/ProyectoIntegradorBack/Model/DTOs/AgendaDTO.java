package com.example.ProyectoIntegradorBack.Model.DTOs;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public record AgendaDTO(Integer id, ProductoDTO producto, Integer cupos,
                        @DateTimeFormat(pattern = "dd/MM/yyyy") Date fechaIda,
                        @DateTimeFormat(pattern = "dd/MM/yyyy") Date fechaVuelta,
                        Boolean estado) {

    public AgendaDTO withProducto(ProductoDTO productoDTO) {
        return new AgendaDTO(id, productoDTO, cupos, fechaIda, fechaVuelta, estado);
    }
}
