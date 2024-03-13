package com.example.ProyectoIntegradorBack.Service.Impl;

import com.example.ProyectoIntegradorBack.Model.Cliente;
import com.example.ProyectoIntegradorBack.Model.DTOs.ClienteDTO;
import com.example.ProyectoIntegradorBack.Repository.IClienteRepository;
import com.example.ProyectoIntegradorBack.Service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private IClienteRepository clienteRepository;

    @Override
    public ClienteDTO save(ClienteDTO cliente) {
        Cliente clienteEntity = new Cliente();
        clienteEntity.setUsuarioId(cliente.usuarioId());
        clienteEntity.setTipoDocumento(cliente.tipoDocumento());
        clienteEntity.setNumeroDocumento(cliente.numeroDocumento());
        clienteEntity.setTelefono(cliente.telefono());
        clienteEntity.setDireccion(cliente.direccion());
        clienteEntity.setCiudad(cliente.ciudad());
        clienteEntity.setPais(cliente.pais());
        clienteRepository.save(clienteEntity);
        return cliente;
    }

    @Override
    public ClienteDTO findById(Integer id) {
        Optional<Cliente> clienteEntityOptional = clienteRepository.findById(id);
        if (clienteEntityOptional.isPresent()) {
            Cliente clienteEntity = clienteEntityOptional.get();
            return new ClienteDTO(
                    clienteEntity.getId(),
                    clienteEntity.getUsuarioId(),
                    clienteEntity.getTipoDocumento(),
                    clienteEntity.getNumeroDocumento(),
                    clienteEntity.getTelefono(),
                    clienteEntity.getDireccion(),
                    clienteEntity.getCiudad(),
                    clienteEntity.getPais(),
                    null
            );
        } else {
            return null;
        }

    }

    @Override
    public ClienteDTO update(ClienteDTO cliente) {
        Cliente clienteEntity = new Cliente();
        clienteEntity.setUsuarioId(cliente.usuarioId());
        clienteEntity.setTipoDocumento(cliente.tipoDocumento());
        clienteEntity.setNumeroDocumento(cliente.numeroDocumento());
        clienteEntity.setTelefono(cliente.telefono());
        clienteEntity.setDireccion(cliente.direccion());
        clienteEntity.setCiudad(cliente.ciudad());
        clienteEntity.setPais(cliente.pais());
        clienteRepository.save(clienteEntity);
        return cliente;
    }

    @Override
    public List<ClienteDTO> findAll() {
        List<Cliente> clienteEntities = clienteRepository.findAll();
        return clienteEntities.stream()
                .map(cliente -> new ClienteDTO(
                        cliente.getId(),
                        cliente.getUsuarioId(),
                        cliente.getTipoDocumento(),
                        cliente.getNumeroDocumento(),
                        cliente.getTelefono(),
                        cliente.getDireccion(),
                        cliente.getCiudad(),
                        cliente.getPais(),
                        null
                ))
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDTO findByUsuarioId(Integer id) {
        Optional<Cliente> clienteEntityOptional = clienteRepository.findByUsuarioId(id);
        return clienteEntityOptional.map(cliente -> new ClienteDTO(
                cliente.getId(),
                cliente.getUsuarioId(),
                cliente.getTipoDocumento(),
                cliente.getNumeroDocumento(),
                cliente.getTelefono(),
                cliente.getDireccion(),
                cliente.getCiudad(),
                cliente.getPais(),
                null
        )).orElse(null);
    }
}
