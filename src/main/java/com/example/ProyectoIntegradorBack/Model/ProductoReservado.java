package com.example.ProyectoIntegradorBack.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ProductoReservado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    private Boolean confirmacion;
    @ManyToOne
    @JoinColumn(name = "producto_id")
    @JsonBackReference
    private Producto producto;
    @ManyToOne
    @JoinColumn(name = "reserva_id")
    @JsonBackReference
    private Reserva reserva;
}
