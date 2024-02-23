package com.example.ProyectoIntegradorBack.Repository;

import com.example.ProyectoIntegradorBack.Model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReservaRepository extends JpaRepository<Reserva, Integer> {
}
