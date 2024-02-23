package com.example.ProyectoIntegradorBack.Service.Impl;

import com.example.ProyectoIntegradorBack.Model.DTOs.ImagenDTO;
import com.example.ProyectoIntegradorBack.Model.DTOs.ProductoDTO;
import com.example.ProyectoIntegradorBack.Model.Producto;
import com.example.ProyectoIntegradorBack.Repository.IProductoRepository;
import com.example.ProyectoIntegradorBack.Service.IProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoService implements IProductoService {

    @Autowired
    private IProductoRepository productoRepository;
    @Autowired
    ObjectMapper mapper;

    @Override
    public ProductoDTO postProducto(ProductoDTO productoDTO) {
        Producto producto = mapper.convertValue(productoDTO, Producto.class);
        productoRepository.save(producto);
        return mapper.convertValue(producto, ProductoDTO.class);
    }

    @Override
    public ProductoDTO getProductoDTO(Integer id) {
        Optional<Producto> optionalProducto = productoRepository.findById(id);
        ProductoDTO productoDTO = null;
        if (optionalProducto.isPresent()) {
            Producto producto = optionalProducto.get();
            productoDTO = convertToDto(producto);
        }
        return productoDTO;
    }

    @Override
    public void updateProducto(ProductoDTO productoDTO) {
        postProducto(productoDTO);
    }

    @Override
    public void deleteProducto(Integer id) {
        productoRepository.deleteById(id);
    }

    @Override
    public Set<ProductoDTO> getAllProductos() {
        List<Producto> productos = productoRepository.findAll();

        Collections.shuffle(productos);
        int maxProductos = Math.min(productos.size(), 10);

        Set<ProductoDTO> productosDto = new HashSet<>();
        Set<Integer> indicesElegidos = new HashSet<>();

        Random random = new Random();
        for (int i = 0; i < maxProductos; i++) {
            int indiceAleatorio;

            do {
                indiceAleatorio = random.nextInt(productos.size());
            } while (indicesElegidos.contains(indiceAleatorio));

            indicesElegidos.add(indiceAleatorio);

            Producto producto = productos.get(indiceAleatorio);
            productosDto.add(convertToDto(producto));
        }

        return productosDto;
    }

    private ProductoDTO convertToDto(Producto producto) {
        List<ImagenDTO> imagenesDto = producto.getImagenes().stream()
                .map(imagen -> new ImagenDTO(imagen.getUrl(), imagen.getAltText()))
                .collect(Collectors.toList());

        ProductoDTO dto = new ProductoDTO(
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getFecha(),
                producto.getCupo(),
                producto.isDisponible(),
                imagenesDto
        );
        return dto;
    }

    public Producto getProducto(Integer id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.orElse(null);
    }
}
