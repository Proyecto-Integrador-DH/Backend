package com.example.ProyectoIntegradorBack.Controller;

import com.example.ProyectoIntegradorBack.Model.DTOs.ProductoDTO;
import com.example.ProyectoIntegradorBack.Service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Collection;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    IProductoService productoService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getProducto(@PathVariable Integer id){
        try {
            ProductoDTO productoDTO = productoService.getProductoDTO(id);
            if (productoDTO != null) {
                return ResponseEntity.ok(productoDTO);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró ningún producto con el ID especificado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hubo un error al procesar la solicitud.");
        }
    }

    @GetMapping("/productos")
    public ResponseEntity<?> getTodosProductos(){
        try {
            Collection<ProductoDTO> productos = productoService.getAllProductos();
            if (productos.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron productos.");
            } else {
            return ResponseEntity.ok(productos);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hubo un error al procesar la solicitud.");
        }
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevoProducto(@RequestBody ProductoDTO productoDTO){
        try {
            productoService.postProducto(productoDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Ya existe un producto con ese nombre.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al procesar la solicitud.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
