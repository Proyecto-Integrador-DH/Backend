package com.example.ProyectoIntegradorBack.Controller;

import com.example.ProyectoIntegradorBack.Model.DTOs.ClienteDTO;
import com.example.ProyectoIntegradorBack.Service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    IClienteService clienteService;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevoCliente(@RequestBody ClienteDTO clienteDTO) {
        try {
            ClienteDTO cliente = clienteService.save(clienteDTO);
            if (cliente != null) {
                return ResponseEntity.ok(cliente);
            } else {
                return ResponseEntity.status(500).body("Hubo un error al procesar la solicitud.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Hubo un error al procesar la solicitud.");
        }
    }

    @PostMapping("/actualizar")
    public ResponseEntity<?> actualizarCliente(@RequestBody ClienteDTO clienteDTO) {
        try {
            ClienteDTO cliente = clienteService.update(clienteDTO);
            if (cliente != null) {
                return ResponseEntity.ok(cliente);
            } else {
                return ResponseEntity.status(500).body("Hubo un error al procesar la solicitud.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Hubo un error al procesar la solicitud.");
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarCliente(@PathVariable Integer id) {
        try {
            ClienteDTO cliente = clienteService.findByUsuarioId(id);
            if (cliente != null) {
                return ResponseEntity.ok(cliente);
            } else {
                return ResponseEntity.status(500).body("Hubo un error al procesar la solicitud.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Hubo un error al procesar la solicitud.");
        }
    }

}
