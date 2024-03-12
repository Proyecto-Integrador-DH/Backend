package com.example.ProyectoIntegradorBack.Service.Impl;

import com.amazonaws.services.glue.model.EntityNotFoundException;
import com.example.ProyectoIntegradorBack.Model.Agenda;
import com.example.ProyectoIntegradorBack.Model.DTOs.AgendaDTO;
import com.example.ProyectoIntegradorBack.Model.DTOs.ProductoDTO;
import com.example.ProyectoIntegradorBack.Model.Producto;
import com.example.ProyectoIntegradorBack.Repository.IAgendaRepository;
import com.example.ProyectoIntegradorBack.Repository.IProductoRepository;
import com.example.ProyectoIntegradorBack.Service.IAgendaService;
import com.example.ProyectoIntegradorBack.Service.IProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendaService implements IAgendaService {
    @Autowired
    private IAgendaRepository agendaRepository;
    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    ObjectMapper mapper;

    @Override
    public AgendaDTO getAgendaById(Integer id) {
        Agenda agenda = agendaRepository.findById(id).orElse(null);
        return mapper.convertValue(agenda, AgendaDTO.class);
    }

    @Override
    public AgendaDTO postAgenda(AgendaDTO agendaDTO) {
        try {
            ProductoDTO productoDTO = agendaDTO.producto();
            Producto producto = productoRepository.findById(productoDTO.Id())
                    .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

            Agenda agenda = new Agenda();
            agenda.setProducto(producto);
            agenda.setCupos(agendaDTO.cupos());
            agenda.setFechaIda(agendaDTO.fechaIda());
            agenda.setFechaVuelta(agendaDTO.fechaVuelta());
            agenda.setEstado(agendaDTO.estado());
            agendaRepository.save(agenda);
            return mapper.convertValue(agenda, AgendaDTO.class);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("Error: Producto no encontrado", e);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la Agenda", e);
        }
    }

    @Override
    public List<AgendaDTO> allAgendas() {
        List<Agenda> agendas = agendaRepository.findAll();;
        List<AgendaDTO> agendaDTOs = new ArrayList<>();
        for (Agenda agenda : agendas) {
            AgendaDTO agendaDTO = mapper.convertValue(agenda, AgendaDTO.class);
            Producto producto = agenda.getProducto();
            ProductoDTO productoDTO = mapper.convertValue(producto, ProductoDTO.class);
            agendaDTO = agendaDTO.withProducto(productoDTO);
            agendaDTOs.add(agendaDTO);
        }
        return agendaDTOs;
    }

    @Override
    public List<AgendaDTO> getAgendasByProductId(Integer id) {
        List<Agenda> agendas = agendaRepository.findByProductoId(id);
        List<AgendaDTO> agendaDTOs = new ArrayList<>();
        for (Agenda agenda : agendas) {
            AgendaDTO agendaDTO = mapper.convertValue(agenda, AgendaDTO.class);
            Producto producto = agenda.getProducto();
            ProductoDTO productoDTO = mapper.convertValue(producto, ProductoDTO.class);
            agendaDTO = agendaDTO.withProducto(productoDTO);
            agendaDTOs.add(agendaDTO);
        }
        return agendaDTOs;
    }

    @Override
    public List<AgendaDTO> getAgendasByCategoryIdByFechas(Integer id, Date fechaIda, Date fechaVuelta) {
        List<Agenda> agendas = agendaRepository.findByProductoCategoriaIdAndFechaIdaGreaterThanEqualAndFechaVueltaLessThanEqual(id, fechaIda, fechaVuelta);
        List<AgendaDTO> agendaDTOs = new ArrayList<>();
        for (Agenda agenda : agendas) {
            AgendaDTO agendaDTO = mapper.convertValue(agenda, AgendaDTO.class);
            Producto producto = agenda.getProducto();
            ProductoDTO productoDTO = mapper.convertValue(producto, ProductoDTO.class);
            agendaDTO = agendaDTO.withProducto(productoDTO);
            agendaDTOs.add(agendaDTO);
        }
        return agendaDTOs;
    }
}
