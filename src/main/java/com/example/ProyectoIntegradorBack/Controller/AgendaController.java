package com.example.ProyectoIntegradorBack.Controller;

import com.example.ProyectoIntegradorBack.Model.DTOs.AgendaDTO;
import com.example.ProyectoIntegradorBack.Service.IAgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping("/agenda")
public class AgendaController {
    @Autowired
    IAgendaService agendaService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllAgendas(){
        try {
            Collection<AgendaDTO> agendas = agendaService.allAgendas();
            if (agendas.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron agendas.");
            } else {
                return ResponseEntity.ok(agendas);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hubo un error al procesar la solicitud.");
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getAgendaById(Integer id){
        try {
            AgendaDTO agendaDTO = agendaService.getAgendaById(id);
            if (agendaDTO != null) {
                return ResponseEntity.ok(agendaDTO);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró ninguna agenda con el ID especificado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hubo un error al procesar la solicitud.");
        }
    }

    @PostMapping("/nueva")
    public ResponseEntity<?> nuevaAgenda(@RequestBody AgendaDTO agendaDTO){
        try {
            AgendaDTO agenda = agendaService.postAgenda(agendaDTO);
            if (agenda != null) {
                return ResponseEntity.ok(agenda);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hubo un error al procesar la solicitud.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hubo un error al procesar la solicitud.");
        }
    }

    @GetMapping("/producto/{id}")
    public ResponseEntity<?> getAgendasByProductId(@PathVariable Integer id){
        try {
            Collection<AgendaDTO> agendas = agendaService.getAgendasByProductId(id);
            if (agendas.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron agendas.");
            } else {
                return ResponseEntity.ok(agendas);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hubo un error al procesar la solicitud.");
        }
    }

    @GetMapping("/categoria/{id}/fechas")
    public ResponseEntity<?> getAgendasByCategoryIdByFechas(@PathVariable Integer id,
                                                            @RequestParam("fechaIda") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaIda,
                                                            @RequestParam("fechaVuelta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaVuelta) {
        try {
            // Aquí deberías llamar al servicio correspondiente para obtener las agendas basadas en los parámetros recibidos
            Collection<AgendaDTO> agendas = agendaService.getAgendasByCategoryIdByFechas(id, fechaIda, fechaVuelta);
            if (agendas.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron agendas.");
            } else {
                return ResponseEntity.ok(agendas);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hubo un error al procesar la solicitud.");
        }
    }
}
