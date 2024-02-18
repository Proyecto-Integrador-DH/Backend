package com.example.ProyectoIntegradorBack.Model;

public class ProductoDTO {
    private Integer id;
    private String nombreCompleto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public ProductoDTO(Integer id, String nombreCompleto) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
    }

    public ProductoDTO(String nombre, String apellido){
        this.nombreCompleto= nombre+ "" + apellido;
    }

}
