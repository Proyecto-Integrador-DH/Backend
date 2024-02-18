package com.example.ProyectoIntegradorBack.Controller;

import com.example.ProyectoIntegradorBack.Model.ProductoDTO;
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
    public ProductoDTO getProducto(@PathVariable Integer id){
        return productoService.verProducto(id);
    }

    @GetMapping("/productos")
    public Collection<ProductoDTO> getTodosProductos(){
        return productoService.listarTodos();
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevoProducto(@RequestBody ProductoDTO productoDTO){
        try {
            productoService.registrarProducto(productoDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Ya existe un producto con ese nombre.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al procesar la solicitud.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
