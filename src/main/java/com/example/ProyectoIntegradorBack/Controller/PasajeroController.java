package com.example.ProyectoIntegradorBack.Controller;

import com.example.ProyectoIntegradorBack.Model.PasajeroDTO;
import com.example.ProyectoIntegradorBack.Service.IPasajeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/viajes")
public class PasajeroController {
    @Autowired
    IPasajeroService pasajeroService;

    @GetMapping("/{id}")
    public PasajeroDTO getPasajero(@PathVariable Integer id){
        return pasajeroService.verPasajero(id);
    }

    @GetMapping
    public Collection<PasajeroDTO> getTodosPasajeros(){
        return pasajeroService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<?> nuevoPasajero(@RequestBody PasajeroDTO pasajeroDTO){
        pasajeroService.registrarPasajero(pasajeroDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }






}



