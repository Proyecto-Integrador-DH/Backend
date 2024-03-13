package com.example.ProyectoIntegradorBack.Controller;

import com.example.ProyectoIntegradorBack.Model.DTOs.ReservaDTO;
import com.example.ProyectoIntegradorBack.Service.IReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/reserva")
public class ReservaController {
    @Autowired
    IReservaService reservaService;

    @PostMapping("/nueva")
    public ResponseEntity<?> nuevaReserva(@RequestBody ReservaDTO reservaDTO){
        try {
            ReservaDTO reserva = reservaService.postReserva(reservaDTO);
            return ResponseEntity.ok(reserva);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hubo un error al procesar la solicitud." + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReservaById(@RequestBody Integer id){
        try {
            ReservaDTO reserva = reservaService.getReservaById(id);
            return ResponseEntity.ok(reserva);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hubo un error al procesar la solicitud." + e.getMessage());
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarReserva(@RequestBody ReservaDTO reservaDTO){
        try {
            ReservaDTO reserva = reservaService.updateReserva(reservaDTO);
            return ResponseEntity.ok(reserva);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hubo un error al procesar la solicitud." + e.getMessage());
        }
    }

    @GetMapping("/reservas")
    public ResponseEntity<?> getTodasReservas(){
        try {
            Collection<ReservaDTO> reservas = reservaService.allReservas();
            if (reservas.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron reservas.");
            } else {
                return ResponseEntity.ok(reservas);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hubo un error al procesar la solicitud.");
        }
    }
}
