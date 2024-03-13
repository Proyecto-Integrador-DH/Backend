package com.example.ProyectoIntegradorBack.Controller;

import com.example.ProyectoIntegradorBack.Model.Icono;
import com.example.ProyectoIntegradorBack.Service.Impl.IconoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/icono")
public class IconoController {

    @Autowired
    private IconoService iconoService;

    @PostMapping("/subir")
    public ResponseEntity<?> guardarIcono(@RequestBody Icono icono){
        try{
           iconoService.guardarIcono(icono);
           return ResponseEntity.ok("Iconos guardadas con éxito.");
        }catch(Exception e){
            return ResponseEntity.status(500).body(e.getLocalizedMessage());
            //return ResponseEntity.status(500).body("Hubo un error al procesar la solicitud.");
        }
    }
    @GetMapping
    public ResponseEntity<?> listarIconos(){
        try{
            return ResponseEntity.ok(iconoService.getIconos());
        }catch(Exception e){
            return ResponseEntity.status(500).body("Hubo un error al procesar la solicitud.");
        }
    }

    @PutMapping
    public ResponseEntity<?> actualizarIcono(@RequestBody Icono icono){
        try{
            iconoService.guardarIcono(icono);
            return ResponseEntity.ok("Iconos actualizados con éxito.");
        }catch(Exception e){
            return ResponseEntity.status(500).body("Hubo un error al procesar la solicitud.");
        }
    }

    @DeleteMapping("/{iconoId}")
    public ResponseEntity<?> eliminarIcono(@PathVariable Integer iconoId){
        try{
            iconoService.eliminarIcono(iconoId, true );
            return ResponseEntity.ok("Icono eliminado con éxito.");
        }catch(Exception e){
            if(e.getMessage().contains("no existe"))
                return ResponseEntity.status(500).body(e.getMessage());

            return ResponseEntity.status(500).body("Hubo un error al procesar la solicitud.");
        }
    }

}
