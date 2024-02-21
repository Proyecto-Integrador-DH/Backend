package com.example.ProyectoIntegradorBack.Service;

import com.example.ProyectoIntegradorBack.Model.Producto;
import com.example.ProyectoIntegradorBack.Model.ProductoDTO;
import com.example.ProyectoIntegradorBack.Repository.IProductoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class ProductoService implements IProductoService{
    @Autowired
    private IProductoRepository productoRepository;
    @Autowired
    ObjectMapper mapper;


    @Override
    public void registrarProducto(ProductoDTO productoDTO) {
        Producto producto = mapper.convertValue(productoDTO, Producto.class);
        productoRepository.save(producto);
    }

    @Override
    public ProductoDTO verProducto(int id) {
        Optional<Producto> optionalProducto = productoRepository.findById(id);
        ProductoDTO productoDTO = null;
        if (optionalProducto.isPresent()) {
            Producto producto = optionalProducto.get();
            productoDTO = mapper.convertValue(producto, ProductoDTO.class);
        }
        return productoDTO;
    }

    @Override
    public void modificarProducto(ProductoDTO productoDTO) {

    }

    @Override
    public void eliminarProducto(int id) {

    }

    @Override
    public Set<ProductoDTO> listarProductos() {
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
            productosDto.add(mapper.convertValue(producto, ProductoDTO.class));
        }

        return productosDto;
    }

    //para utilizar en las pruebas unitarias
    public void setProductoRepository(IProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

}





