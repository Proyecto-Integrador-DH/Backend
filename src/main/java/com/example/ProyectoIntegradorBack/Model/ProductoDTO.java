package com.example.ProyectoIntegradorBack.Model;

import java.util.Date;
import java.util.List;

public record ProductoDTO(String nombre, String descripcion, Date fecha, int cupo, boolean activo, List<Imagen> imagenes){
}
