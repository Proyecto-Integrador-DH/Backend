package com.example.ProyectoIntegradorBack.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "producto_id")
    @JsonBackReference
    private Producto producto;
    @OneToMany
    @JoinColumn(name = "agenda_id")
    @JsonIgnore
    private List<Reserva> reservas;
    private Integer cupos;

    private Date fechaIda;
    private Date fechaVuelta;
    private Boolean estado;

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public Date getFechaIda() {
        return fechaIda;
    }

    public void setFechaIda(Date fechaIda) {
        this.fechaIda = fechaIda;
    }

    public Date getFechaVuelta() {
        return fechaVuelta;
    }

    public void setFechaVuelta(Date fechaVuelta) {
        this.fechaVuelta = fechaVuelta;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setCupos(Integer cupos) {
        this.cupos = cupos;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Producto getProducto() {
        return producto;
    }

    public Integer getCupos() {
        return cupos;
    }

    public Boolean getEstado() {
        return estado;
    }
}
