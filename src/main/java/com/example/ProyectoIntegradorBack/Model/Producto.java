package com.example.ProyectoIntegradorBack.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    private String nombre;
    private String descripcion;
    private Date fecha;
    private int cupo;
    private boolean disponible;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @JsonBackReference
    private Categoria categoria;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "producto_id")
    @JsonIgnore
    private List<Imagen> imagenes;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "producto_id")
    @JsonIgnore
    private List<ProductoReservado> productosReservados;
    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getCupo() {
        return cupo;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public List<Imagen> getImagenes() {
        return imagenes;
    }

    public Integer getId() {
        return id;
    }
}
