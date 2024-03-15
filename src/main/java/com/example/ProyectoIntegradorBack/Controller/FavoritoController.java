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

    @GetMapping("/cliente/{clienteId}/favorito/{productoId}")
    public ResponseEntity<?> favoritoByIdClient(@PathVariable Integer clienteId, @PathVariable Integer productoId) {
        try {
            return ResponseEntity.ok(favoritoService.favoritoByIdClient(clienteId, productoId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFavorito(@RequestBody FavoritoDTO favoritoDTO) {
        try {
            favoritoService.delete(favoritoDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
