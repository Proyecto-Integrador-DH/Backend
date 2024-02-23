package com.example.ProyectoIntegradorBack.Repository;

import com.example.ProyectoIntegradorBack.Model.ProductoReservado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoReservado extends JpaRepository<ProductoReservado, Integer> {
}
