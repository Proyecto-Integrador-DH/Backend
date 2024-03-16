package com.example.ProyectoIntegradorBack.Service.Impl;

import com.amazonaws.services.glue.model.EntityNotFoundException;
import com.example.ProyectoIntegradorBack.Model.Agenda;
import com.example.ProyectoIntegradorBack.Model.Cliente;
import com.example.ProyectoIntegradorBack.Model.Producto;
import com.example.ProyectoIntegradorBack.Model.DTOs.AgendaDTO;
import com.example.ProyectoIntegradorBack.Model.DTOs.ClienteDTO;
import com.example.ProyectoIntegradorBack.Model.DTOs.ReservaDTO;
import com.example.ProyectoIntegradorBack.Model.DTOs.ProductoDTO;
import com.example.ProyectoIntegradorBack.Model.Reserva;
import com.example.ProyectoIntegradorBack.Repository.IAgendaRepository;
import com.example.ProyectoIntegradorBack.Repository.IClienteRepository;
import com.example.ProyectoIntegradorBack.Repository.IReservaRepository;
import com.example.ProyectoIntegradorBack.Service.IReservaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaService implements IReservaService {
    @Autowired
    private IReservaRepository reservaRepository;
    @Autowired
    private IAgendaRepository agendaRepository;
    @Autowired
    private IClienteRepository clienteRepository;
    @Autowired
    ObjectMapper mapper;
    @Override
    public ReservaDTO postReserva(ReservaDTO reservaDTO) {
        try {
            AgendaDTO agendaDTO = reservaDTO.agenda();
            Agenda agenda = agendaRepository.findById(agendaDTO.id())
                    .orElseThrow(() -> new EntityNotFoundException("Agenda no encontrada"));

            ClienteDTO clienteDTO = reservaDTO.cliente();
            Cliente cliente = clienteRepository.findById(clienteDTO.id())
                    .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));

            if (agenda.getCupos() >= reservaDTO.cantidad()) {
                agenda.setCupos(agenda.getCupos() - reservaDTO.cantidad());
                if (agenda.getCupos() == 0) {
                    agenda.setEstado(false);
                }

                Reserva reserva = new Reserva();
                reserva.setAgenda(agenda);
                reserva.setCliente(cliente);
                reserva.setCantidad(reservaDTO.cantidad());
                reserva.setEstado(reservaDTO.estado());
                reservaRepository.save(reserva);
                agendaRepository.save(agenda);
                return mapper.convertValue(reserva, ReservaDTO.class);
            } else {
                throw new RuntimeException("Error: No hay cupos disponibles");
            }
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("Error: Agenda o cliente no encontrados", e);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la Reserva", e);
        }
    }

    @Override
    public ReservaDTO getReservaById(Integer id) {
        try {
            Reserva reserva = reservaRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada"));
            return mapper.convertValue(reserva, ReservaDTO.class);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("Error: Reserva no encontrada", e);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la Reserva", e);
        }
    }

    @Override
    public ReservaDTO updateReserva(ReservaDTO reservaDTO) {
        try {
            Reserva reserva = reservaRepository.findById(reservaDTO.id())
                    .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada"));
            reserva.setCantidad(reservaDTO.cantidad());
            reserva.setEstado(reservaDTO.estado());
            reservaRepository.save(reserva);
            return mapper.convertValue(reserva, ReservaDTO.class);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("Error: Reserva no encontrada", e);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la Reserva", e);
        }
    }

    @Override
    public List<ReservaDTO> allReservas() {
        try {
            List<Reserva> reservas = reservaRepository.findAll();
            return reservas.stream()
                    .map(reserva -> mapper.convertValue(reserva, ReservaDTO.class))
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las Reservas", e);
        }
    }

    @Override
    public List<ReservaDTO> getReservasByCliente(Integer id) {
        try {
            List<Reserva> reservas = reservaRepository.findByClienteId(id);
            return reservas.stream()
                    .map(reserva -> {
                        ReservaDTO reservaDTO = mapper.convertValue(reserva, ReservaDTO.class);
                        reservaDTO = reservaDTO.withAgenda(mapToAgendaDTO(reserva.getAgenda()));
                        return reservaDTO;
                    })
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las Reservas", e);
        }
    }

    private AgendaDTO mapToAgendaDTO(Agenda agenda) {
        if (agenda == null) {
            return null;
        }
        return new AgendaDTO(agenda.getId(), mapToProductoDTO(agenda.getProducto()), agenda.getCupos(),
                agenda.getFechaIda(), agenda.getFechaVuelta(), agenda.getEstado());
    }

    private ProductoDTO mapToProductoDTO(Producto producto) {
        if (producto == null) {
            return null;
        }
        return new ProductoDTO(producto.getId(), producto.getNombre(), producto.getDescripcion(), producto.isDisponible());
    }
}
