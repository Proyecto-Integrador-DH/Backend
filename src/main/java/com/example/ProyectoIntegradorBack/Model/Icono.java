package com.example.ProyectoIntegradorBack.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Icono {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Transient
    private String iconPath;
    private String url;
    private String alt;
    private String nombre;
    public String getIconPath() { return iconPath;}
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setUrl(String url) { this.url = url; }

    public void setAlt(String alt) { this.alt = alt; }

    public String getUrl() {return url;}

    public String getAlt() {return alt;}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Icono{" +
                "id=" + id +
                ", iconPath='" + iconPath + '\'' +
                ", url='" + url + '\'' +
                ", alt='" + alt + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}

