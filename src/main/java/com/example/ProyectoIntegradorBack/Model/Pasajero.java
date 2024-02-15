package com.example.ProyectoIntegradorBack.Model;

import jakarta.persistence.*;

@Entity
public class Pasajero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    private String nombre;
    private String apellido;
    private String direccion;


}
