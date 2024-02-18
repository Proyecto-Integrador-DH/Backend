package com.example.ProyectoIntegradorBack.Controller;

import com.example.ProyectoIntegradorBack.Model.ProductoDTO;
import com.example.ProyectoIntegradorBack.Service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/viajes")
public class ProductoController {
    @Autowired
    IProductoService productoService;

    @GetMapping("/{id}")
    public ProductoDTO getProducto(@PathVariable int id){
        return productoService.verProducto(id);
    }

    @GetMapping
    public Collection<ProductoDTO> getTodosProductos(){
        return productoService.listarProductos();
    }

    @PostMapping
    public ResponseEntity<?> nuevoProducto(@RequestBody ProductoDTO productoDTO){
        productoService.registrarProducto(productoDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }






}



