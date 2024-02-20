package com.example.ProyectoIntegradorBack.Repository;

import com.example.ProyectoIntegradorBack.Model.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IImagenRepository extends JpaRepository<Imagen, Integer> {
    List<Imagen> findByProductoId(Integer productoId);
}
