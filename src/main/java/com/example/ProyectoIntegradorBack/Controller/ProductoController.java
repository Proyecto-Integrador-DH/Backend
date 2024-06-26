package com.example.ProyectoIntegradorBack.Controller;

import com.example.ProyectoIntegradorBack.Model.Categoria;
import com.example.ProyectoIntegradorBack.Model.DTOs.CategoriaDTO;
import com.example.ProyectoIntegradorBack.Model.DTOs.CategoriaNombreDTO;
import com.example.ProyectoIntegradorBack.Model.DTOs.NuevoProductoDTO;
import com.example.ProyectoIntegradorBack.Model.DTOs.ProductoDTO;
import com.example.ProyectoIntegradorBack.Model.Producto;
import com.example.ProyectoIntegradorBack.Service.ICategoriaService;
import com.example.ProyectoIntegradorBack.Service.IProductoService;
import com.example.ProyectoIntegradorBack.Utils.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    IProductoService productoService;
    @Autowired
    ICategoriaService categoriaService;
    @Autowired
    private AuthenticationService  AuthenticationService;
    Boolean tieneRolAdmin = false;

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
    public ResponseEntity<?> getTodosProductosRandom(){
        try {
            Collection<ProductoDTO> productos = productoService.getAllProductosRandom();
            if (productos.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron productos.");
            } else {
            return ResponseEntity.ok(productos);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hubo un error al procesar la solicitud.");
        }
    }

    @GetMapping("/productosAll")
    public ResponseEntity<?> getTodosProductosAll(){
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
    public ResponseEntity<?> nuevoProducto(@RequestHeader("Authorization") String token, @RequestBody NuevoProductoDTO productoDTO){
        try {
            System.out.println("Nuevo producto: " + productoDTO.getNombre());
            tieneRolAdmin = AuthenticationService.getRolesFromToken(token);
            if(!tieneRolAdmin){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No tiene permisos para realizar esta acción.");
            }
            Producto producto = productoService.postProducto(productoDTO);
            Integer id = producto.getId();
            System.out.println("ID del producto creado: " + id);
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Ya existe un producto con ese nombre.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Error al procesar la solicitud.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/addCategoria/{idProducto}/{idCategoria}")
    //public ResponseEntity<?> addCategoria(@PathVariable Integer idProducto, @PathVariable Integer idCategoria){
    public ResponseEntity<?> addCategoria(@RequestHeader("Authorization") String token, @PathVariable Integer idProducto, @PathVariable Integer idCategoria){
            try {
            tieneRolAdmin = AuthenticationService.getRolesFromToken(token);
                //tieneRolAdmin = true;
            if(!tieneRolAdmin){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No tiene permisos para realizar esta acción.");
            }
            CategoriaNombreDTO categoriaDTO = categoriaService.getCategoria(idCategoria);
            productoService.addCategoria(idProducto,categoriaDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Categoría agregada correctamente.");
        } catch (Exception e) {
                System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hubo un error al procesar la solicitud.");
        }
    }

    @PutMapping("/addCaracteristicas/{idProducto}")
    public ResponseEntity<?> updateProductoCaracteristicas(@RequestHeader("Authorization") String token, @PathVariable Integer idProducto, @RequestBody List<Integer> caracteristicas){
        try {
            tieneRolAdmin = AuthenticationService.getRolesFromToken(token);
            if(!tieneRolAdmin){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No tiene permisos para realizar esta acción.");
            }
            productoService.updateProductoCaracteristicas(idProducto, caracteristicas);
            return ResponseEntity.status(HttpStatus.OK).body("Producto actualizado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hubo un error al procesar la solicitud.");
        }
    }

    @DeleteMapping("/deleteProducto/{id}")
    public ResponseEntity<?> deleteProducto(@RequestHeader("Authorization") String token, @PathVariable Integer id){
        try {
            tieneRolAdmin = AuthenticationService.getRolesFromToken(token);
            if(!tieneRolAdmin){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No tiene permisos para realizar esta acción.");
            }
            ProductoDTO producto = productoService.getProductoDTO(id);
            if (producto == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró ningún producto con el ID especificado.");
            }
            System.out.println("Producto a eliminar: " + producto.nombre() + " " + producto.Id());
            productoService.deleteProducto(id);
            return ResponseEntity.status(HttpStatus.OK).body("Producto eliminado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hubo un error al procesar la solicitud.");
        }
    }
}
