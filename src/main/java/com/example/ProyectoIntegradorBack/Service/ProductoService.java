package com.example.ProyectoIntegradorBack.Service;

import com.example.ProyectoIntegradorBack.Model.Producto;
import com.example.ProyectoIntegradorBack.Model.ProductoDTO;
import com.example.ProyectoIntegradorBack.Repository.IProductoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductoService implements IProductoService {

    @Autowired
    private IProductoRepository productoRepository;
    @Autowired
    ObjectMapper mapper;

    @Override
    public ProductoDTO registrarProducto(ProductoDTO productoDTO) {
        Producto producto = mapper.convertValue(productoDTO, Producto.class);
        productoRepository.save(producto);
        return mapper.convertValue(producto, ProductoDTO.class);
    }

    @Override
    public ProductoDTO verProducto(Integer id) {
        Optional<Producto> producto = productoRepository.findById(id);
        ProductoDTO productoDTO = null;
        if(producto.isPresent())
            productoDTO = mapper.convertValue(producto, ProductoDTO.class);
        return productoDTO;
    }

    @Override
    public void modificarProducto(ProductoDTO productoDTO) {
        registrarProducto(productoDTO);
    }

    @Override
    public void eliminarProducto(Integer id) {
        productoRepository.deleteById(id);
    }

    @Override
    public Set<ProductoDTO> listarTodos() {
        List<Producto> productos = productoRepository.findAll();
        Set<ProductoDTO> productosDto = new HashSet<>();
        for(Producto producto : productos){
            productosDto.add(mapper.convertValue(producto, ProductoDTO.class));
        }
        return productosDto;
    }

    public Producto getProducto(Integer id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.orElse(null);
    }
}
