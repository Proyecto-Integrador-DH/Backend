package com.example.ProyectoIntegradorBack.Service;

import ch.qos.logback.core.model.INamedModel;
import com.example.ProyectoIntegradorBack.Model.Pasajero;
import com.example.ProyectoIntegradorBack.Model.PasajeroDTO;
import com.example.ProyectoIntegradorBack.Repository.IPasajeroRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class PasajeroService implements IPasajeroService{
    @Autowired
    private IPasajeroRepository pasajeroRepository;
@Autowired
    ObjectMapper mapper;


    @Override
    public void registrarPasajero(PasajeroDTO pasajeroDTO) {
        Pasajero pasajero= mapper.convertValue(pasajeroDTO, Pasajero.class);
        pasajeroRepository.save(pasajero);
    }

    @Override
    public PasajeroDTO verPasajero(Integer id) {
        Optional<Pasajero> pasajero= pasajeroRepository.findById(id);
        PasajeroDTO pasajeroDTO= null;
        if(pasajero.isPresent())
            pasajeroDTO= mapper.convertValue(pasajero, PasajeroDTO.class);
        return pasajeroDTO;
    }

    @Override
    public void modificarPasajero(PasajeroDTO pasajeroDTO) {
        registrarPasajero(pasajeroDTO);
    }

    @Override
    public void eliminarPasajero(Integer id) {
        pasajeroRepository.deleteById(id);
    }

    @Override
    public Set<PasajeroDTO> listarTodos() {
        List<Pasajero> pasajeros= pasajeroRepository.findAll();
        Set<PasajeroDTO> pasajerosDto= new HashSet<>();
        for(Pasajero pasajero: pasajeros){
            pasajerosDto.add(mapper.convertValue(pasajero, PasajeroDTO.class));
        }
        return pasajerosDto;
    }
}
