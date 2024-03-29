package com.example.ProyectoIntegradorBack.Service.Impl;

import com.amazonaws.services.glue.model.EntityNotFoundException;
import com.example.ProyectoIntegradorBack.Model.Agenda;
import com.example.ProyectoIntegradorBack.Model.DTOs.AgendaDTO;
import com.example.ProyectoIntegradorBack.Model.DTOs.CategoriaDTO;
import com.example.ProyectoIntegradorBack.Model.DTOs.ImagenDTO;
import com.example.ProyectoIntegradorBack.Model.DTOs.ProductoDTO;
import com.example.ProyectoIntegradorBack.Model.Producto;
import com.example.ProyectoIntegradorBack.Repository.IAgendaRepository;
import com.example.ProyectoIntegradorBack.Repository.IProductoRepository;
import com.example.ProyectoIntegradorBack.Service.IAgendaService;
import com.example.ProyectoIntegradorBack.Service.IProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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

            Calendar calIda = Calendar.getInstance();
            calIda.setTime(agendaDTO.fechaIda());
            calIda.add(Calendar.DAY_OF_MONTH, 1);
            Date fechaIdaSumada = calIda.getTime();
            agenda.setFechaIda(fechaIdaSumada);

            Calendar calVuelta = Calendar.getInstance();
            calVuelta.setTime(agendaDTO.fechaVuelta());
            calVuelta.add(Calendar.DAY_OF_MONTH, 1);
            Date fechaVueltaSumada = calVuelta.getTime();
            agenda.setFechaVuelta(fechaVueltaSumada);

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
            Optional<Producto> optionalProducto = productoRepository.findById(producto.getId());
            ProductoDTO productoDTO = null;
            if (optionalProducto.isPresent()) {
                producto = optionalProducto.get();
                productoDTO = convertToDto(producto);
            }
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
            Optional<Producto> optionalProducto = productoRepository.findById(producto.getId());
            ProductoDTO productoDTO = null;
            if (optionalProducto.isPresent()) {
                producto = optionalProducto.get();
                productoDTO = convertToDto(producto);
            }
            agendaDTO = agendaDTO.withProducto(productoDTO);
            agendaDTOs.add(agendaDTO);
        }
        return agendaDTOs;
    }

    @Override
    public List<AgendaDTO> getAgendasByFechas(Date fechaIda, Date fechaVuelta) {
        List<Agenda> agendas = agendaRepository.findByFechaIdaBetweenAndCuposGreaterThan(fechaIda, fechaVuelta, 0);
        List<AgendaDTO> agendaDTOs = new ArrayList<>();
        for (Agenda agenda : agendas) {
            AgendaDTO agendaDTO = mapper.convertValue(agenda, AgendaDTO.class);
            Producto producto = agenda.getProducto();
            Optional<Producto> optionalProducto = productoRepository.findById(producto.getId());
            ProductoDTO productoDTO = null;
            if (optionalProducto.isPresent()) {
                producto = optionalProducto.get();
                productoDTO = convertToDto(producto);
            }
            agendaDTO = agendaDTO.withProducto(productoDTO);
            agendaDTOs.add(agendaDTO);
        }
        return agendaDTOs;
    }

    private ProductoDTO convertToDto(Producto producto) {
        CategoriaDTO categoriaDTO = mapper.convertValue(producto.getCategoria(), CategoriaDTO.class);
        List<ImagenDTO> imagenesDto = producto.getImagenes().stream()
                .map(imagen -> new ImagenDTO(imagen.getUrl(), imagen.getAltText()))
                .collect(Collectors.toList());

        ProductoDTO dto = new ProductoDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.isDisponible(),
                producto.getUbicacion(),
                categoriaDTO,
                imagenesDto
        );
        return dto;
    }
}
