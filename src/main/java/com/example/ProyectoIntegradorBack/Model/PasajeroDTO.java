package com.example.ProyectoIntegradorBack.Model;

public class PasajeroDTO {
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

    public PasajeroDTO(Integer id, String nombreCompleto) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
    }

    public PasajeroDTO(String nombre, String apellido){
        this.nombreCompleto= nombre+ "" + apellido;
    }

}
