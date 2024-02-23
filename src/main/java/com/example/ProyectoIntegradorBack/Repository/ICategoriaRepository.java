package com.example.ProyectoIntegradorBack.Repository;

import com.example.ProyectoIntegradorBack.Model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoriaRepository extends JpaRepository<Categoria, Integer> {
}
