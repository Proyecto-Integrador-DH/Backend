package com.example.ProyectoIntegradorBack.Repository;

import com.example.ProyectoIntegradorBack.Model.Pasajero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPasajeroRepository extends JpaRepository<Pasajero, Integer> {
}
