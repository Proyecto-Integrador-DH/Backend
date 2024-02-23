package com.example.ProyectoIntegradorBack.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    private String tipoDocumento;
    private String numeroDocumento;
    private String telefono;
    private String direccion;
    private String ciudad;
    private String pais;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cliente_id")
    @JsonIgnore
    private List<Reserva> reservas;
}
