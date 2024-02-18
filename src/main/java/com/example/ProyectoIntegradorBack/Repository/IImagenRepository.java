package com.example.ProyectoIntegradorBack.Repository;

import com.example.ProyectoIntegradorBack.Model.Imagen;
import com.example.ProyectoIntegradorBack.Model.ImagenDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IImagenRepository extends JpaRepository<Imagen, Integer> {
}
