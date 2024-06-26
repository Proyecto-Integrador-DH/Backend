package com.example.ProyectoIntegradorBack.Service.Impl;

import com.example.ProyectoIntegradorBack.Model.Categoria;
import com.example.ProyectoIntegradorBack.Model.DTOs.*;
import com.example.ProyectoIntegradorBack.Model.Producto;
import com.example.ProyectoIntegradorBack.Repository.ICategoriaRepository;
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
    public Producto postProducto(NuevoProductoDTO productoDTO) {
        Producto producto = mapper.convertValue(productoDTO, Producto.class);
        Producto productoNuevo = productoRepository.save(producto);
        return productoNuevo;
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
    public void updateProducto(NuevoProductoDTO productoDTO) {
        postProducto(productoDTO);
    }

    @Override
    public void updateProductoCaracteristicas(Integer idProducto, List<Integer> caracteristicas) {
        Optional<Producto> optionalProducto = productoRepository.findById(idProducto);
        if (optionalProducto.isPresent()) {
            Producto producto = optionalProducto.get();
            producto.getCaracteristicas().clear();
            producto.getCaracteristicas().addAll(caracteristicas);
            productoRepository.save(producto);
        }
    }

    @Override
    public void deleteProducto(Integer id) {
        productoRepository.deleteById(id);
    }

    @Override
    public Set<ProductoDTO> getAllProductosRandom() {
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

    @Override
    public Set<ProductoDTO> getAllProductos() {
        List<Producto> productos = productoRepository.findAll();
        Set<ProductoDTO> productosDto = new HashSet<>();
        for (Producto producto : productos) {
            productosDto.add(convertToDto(producto));
        }
        return productosDto;
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

    public Producto getProducto(Integer id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.orElse(null);
    }

    @Override
    public void addCategoria(Integer idProducto, CategoriaNombreDTO categoriaDTO) {
        Optional<Producto> optionalProducto = productoRepository.findById(idProducto);
        if (optionalProducto.isPresent()) {
            Producto producto = optionalProducto.get();
            Categoria categoria = new Categoria();
            categoria.setId(categoriaDTO.getId());
            categoria.setNombre(categoriaDTO.getNombre());
            producto.setCategoria(categoria);
            productoRepository.save(producto);
        }
    }
}
