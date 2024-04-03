package com.example.ProyectoIntegradorBack.Service.Impl;

import com.example.ProyectoIntegradorBack.Model.Cliente;
import com.example.ProyectoIntegradorBack.Model.DTOs.*;
import com.example.ProyectoIntegradorBack.Model.Favorito;
import com.example.ProyectoIntegradorBack.Model.Imagen;
import com.example.ProyectoIntegradorBack.Model.Producto;
import com.example.ProyectoIntegradorBack.Repository.IClienteRepository;
import com.example.ProyectoIntegradorBack.Repository.IFavoritoRepository;
import com.example.ProyectoIntegradorBack.Repository.IProductoRepository;
import com.example.ProyectoIntegradorBack.Service.IFavoritoService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            favoritoRepository.save(favorito);
            return mapper.convertValue(favorito, FavoritoDTO.class);
        }
        return null;
    }

    @Override
    public void delete(FavoritoDTO favoritoDTO) {
        Optional<Favorito> optionalFavorito = favoritoRepository.findByClienteIdAndProductoId(favoritoDTO.cliente().id(), favoritoDTO.producto().Id());
        if (optionalFavorito.isPresent()) {
            Favorito favorito = optionalFavorito.get();
            favorito.setEstado(false);
            favoritoRepository.delete(favorito);
        }
    }

    @Override
    public FavoritoDTO favoritoByIdClient(Integer clienteId, Integer productoId) {
        Optional<Favorito> optionalFavorito = favoritoRepository.findByClienteIdAndProductoId(clienteId, productoId);
        if (optionalFavorito.isPresent()) {
            Favorito favorito = optionalFavorito.get();
            return mapper.convertValue(favorito, FavoritoDTO.class);
        }
        return null;
    }

    @Override
    public List<FavoritoDTO> favoritosByClient(Integer id) {
        List<Favorito> favoritos = favoritoRepository.findAllByClienteId(id);
        List<FavoritoDTO> favoritoDTOs = new ArrayList<>();
        for (Favorito favorito : favoritos) {
            FavoritoDTO favoritoDTO = mapper.convertValue(favorito, FavoritoDTO.class);
            Integer productoId = favorito.getProducto().getId();
            Optional<Producto> optionalProducto = productoRepository.findById(productoId);
            ProductoDTO productoDTO = null;
            if (optionalProducto.isPresent()) {
                Producto producto = optionalProducto.get();
                productoDTO = convertToDto(producto);
            }
            favoritoDTO = favoritoDTO.withProducto(productoDTO);
            favoritoDTOs.add(favoritoDTO);
        }
        return favoritoDTOs;
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
                imagenesDto,
                producto.getCaracteristicas()
        );
        return dto;
    }
}
