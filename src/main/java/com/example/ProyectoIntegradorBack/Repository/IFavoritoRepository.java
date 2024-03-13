package com.example.ProyectoIntegradorBack.Repository;

import com.example.ProyectoIntegradorBack.Model.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFavoritoRepository extends JpaRepository<Favorito, Integer> {
}
