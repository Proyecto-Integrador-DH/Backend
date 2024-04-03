package com.example.ProyectoIntegradorBack.Model.DTOs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public record ProductoDTOCat(Integer Id, String nombre, String descripcion, boolean disponible, String ubicacion, List<Integer> caracteristicas, List<ImagenDTO> imagenes){
}
