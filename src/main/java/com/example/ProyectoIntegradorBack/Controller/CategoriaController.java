package com.example.ProyectoIntegradorBack.Controller;

import com.example.ProyectoIntegradorBack.Model.DTOs.CategoriaDTO;
import com.example.ProyectoIntegradorBack.Model.DTOs.CategoriaNombreDTO;
import com.example.ProyectoIntegradorBack.Service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    ICategoriaService categoriaService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoria(@PathVariable Integer id){
        try {
            CategoriaNombreDTO categoriaDTO = categoriaService.getCategoria(id);it a
            if (categoriaDTO != null) {
                return ResponseEntity.ok(categoriaDTO);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró ninguna categoría con el ID especificado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hubo un error al procesar la solicitud.");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCategorias(){
        try {
            Collection<CategoriaNombreDTO> categorias = categoriaService.getAllCategorias();
            if (categorias.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron categorías.");
            } else {
                return ResponseEntity.ok(categorias);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hubo un error al procesar la solicitud.");
        }
    }
}
