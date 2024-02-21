package com.example.ProyectoIntegradorBack.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private Date date;
    private int availability;
    //private Enum actions;
    private int imagesId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

   // public Enum getActions() {
    //    return actions;
  //  }

    //public void setActions(Enum actions) {
      //  this.actions = actions;
   // }

    public int getImagesId() {
        return imagesId;
    }

    public void setImagesId(int imagesId) {
        this.imagesId = imagesId;
    }

    public Producto(int id, String name, String description, Date date, int availability, int imagesId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.availability = availability;
        //this.actions = actions;
        this.imagesId = imagesId;
    }

    public Producto() {
    }
}