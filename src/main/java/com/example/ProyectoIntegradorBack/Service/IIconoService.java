package com.example.ProyectoIntegradorBack.Service;

import com.example.ProyectoIntegradorBack.Model.Icono;

import java.util.List;

public interface IIconoService {

   void guardarIcono(Icono icono);
   List<Icono> getIconos();

}
