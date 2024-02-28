package com.example.ProyectoIntegradorBack.Model.DTOs;

import java.util.Date;
import java.util.List;

public record ProductoDTO(Integer Id, String nombre, String descripcion, Date fecha, int cupo, boolean disponible, List<ImagenDTO> imagenes){
}
