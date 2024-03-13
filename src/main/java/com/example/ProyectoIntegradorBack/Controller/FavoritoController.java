package com.example.ProyectoIntegradorBack.Controller;

import com.example.ProyectoIntegradorBack.Model.DTOs.FavoritoDTO;
import com.example.ProyectoIntegradorBack.Service.IFavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favoritos")
public class FavoritoController {
    @Autowired
    private IFavoritoService favoritoService;

    @PostMapping("/save")
    public ResponseEntity<?> saveFavorito(@RequestBody FavoritoDTO favoritoDTO) {
        try {
            return ResponseEntity.ok(favoritoService.save(favoritoDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateFavorito(@RequestBody FavoritoDTO favoritoDTO) {
        try {
            return ResponseEntity.ok(favoritoService.update(favoritoDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<?> favoritoByIdClient(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(favoritoService.favoritoByIdClient(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
