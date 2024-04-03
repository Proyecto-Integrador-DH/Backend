package com.example.ProyectoIntegradorBack.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.ArrayList;
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
    private boolean disponible;
    private String ubicacion;
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
    private List<Agenda> agendas;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "producto_id")
    @JsonIgnore
    private List<Favorito> favoritos;

    @ElementCollection
    @CollectionTable(name = "producto_caracteristicas", joinColumns = @JoinColumn(name = "producto_id"))
    @Column(name = "caracteristicas")
    private List<Integer> caracteristicas = new ArrayList<>();

    public List<Integer> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<Integer> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public void setCaracteristicas(ArrayList<Integer> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
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

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
