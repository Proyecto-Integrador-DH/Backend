package com.example.ProyectoIntegradorBack.Model;

import com.example.ProyectoIntegradorBack.Model.DTOs.ProductoDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Transient
    private List<String> imgPath;
    private String url;
    private String altText;
    @ManyToOne
    @JoinColumn(name = "producto_id")
    @JsonBackReference
    private Producto producto;
    public List<String> getImgPath() {
        return imgPath;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Producto getProducto() {
        return producto;
    }

    public String getAltText() {
        return altText;
    }

    public String getUrl() {
        return url;
    }
}
