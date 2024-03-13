package com.example.ProyectoIntegradorBack.Service.Impl;

import com.example.ProyectoIntegradorBack.Model.Cliente;
import com.example.ProyectoIntegradorBack.Model.DTOs.FavoritoDTO;
import com.example.ProyectoIntegradorBack.Model.Favorito;
import com.example.ProyectoIntegradorBack.Model.Producto;
import com.example.ProyectoIntegradorBack.Repository.IClienteRepository;
import com.example.ProyectoIntegradorBack.Repository.IFavoritoRepository;
import com.example.ProyectoIntegradorBack.Repository.IProductoRepository;
import com.example.ProyectoIntegradorBack.Service.IFavoritoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoritoService implements IFavoritoService {
    @Autowired
    private IFavoritoRepository favoritoRepository;

    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    ObjectMapper mapper;
    @Override
    public FavoritoDTO save(FavoritoDTO favoritoDTO) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(favoritoDTO.cliente().id());
        Optional<Producto> optionalProducto = productoRepository.findById(favoritoDTO.producto().Id());

        if (optionalCliente.isPresent() && optionalProducto.isPresent()) {
            Cliente cliente = optionalCliente.get();
            Producto producto = optionalProducto.get();

            Favorito favorito = new Favorito();
            favorito.setCliente(optionalCliente.get());
            favorito.setProducto(optionalProducto.get());
            favorito.setEstado(true);
            System.out.println(favorito.getCliente());
            favoritoRepository.save(favorito);
            return mapper.convertValue(favorito, FavoritoDTO.class);
        }
        return null;
    }

    @Override
    public FavoritoDTO update(FavoritoDTO favoritoDTO) {
        favoritoDTO = favoritoDTO.withEstado(!favoritoDTO.estado());
        Favorito favorito = mapper.convertValue(favoritoDTO, Favorito.class);
        favoritoRepository.save(favorito);
        return mapper.convertValue(favorito, FavoritoDTO.class);
    }

    @Override
    public FavoritoDTO favoritoByIdClient(Integer id) {
        Optional<Favorito> favorito = favoritoRepository.findByClienteId(id);
        if (favorito.isPresent()) {
            return mapper.convertValue(favorito.get(), FavoritoDTO.class);
        }
        return null;
    }
}
