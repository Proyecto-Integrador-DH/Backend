package com.example.ProyectoIntegradorBack.Repository;

import com.example.ProyectoIntegradorBack.Model.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAgendaRepository extends JpaRepository<Agenda, Integer> {
    List<Agenda> findByProductoId(Integer id);
}
