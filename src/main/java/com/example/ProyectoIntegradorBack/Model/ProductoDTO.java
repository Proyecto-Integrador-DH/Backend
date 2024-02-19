package com.example.ProyectoIntegradorBack.Model;

public class ProductoDTO {
    private int id;
    private String name;
    private String description;
    private int imagesId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName(String producto1) {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription(String prueba1) {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImagesId(String imagen1) {
        return imagesId;
    }

    public void setImagesId(int imagesId) {
        this.imagesId = imagesId;
    }

    public ProductoDTO(int id, String name, String description, int imagesId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imagesId = imagesId;
    }


    public ProductoDTO() {
    }
}
