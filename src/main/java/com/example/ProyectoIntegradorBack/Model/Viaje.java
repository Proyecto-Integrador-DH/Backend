package com.example.ProyectoIntegradorBack.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "viajes")
public class Viaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "Nombre del viaje")
    private String nombreViaje;
}
