package com.example.ProyectoIntegradorBack.Service;

import com.example.ProyectoIntegradorBack.Model.DTOs.NuevoProductoDTO;
import com.example.ProyectoIntegradorBack.Model.DTOs.ProductoDTO;
import com.example.ProyectoIntegradorBack.Model.Producto;

import java.util.Set;

public interface IProductoService {
    Producto postProducto(NuevoProductoDTO productoDTO);
    ProductoDTO getProductoDTO(Integer id);

    void updateProducto(NuevoProductoDTO productoDTO);
    void deleteProducto(Integer id);
    Set<ProductoDTO> getAllProductosRandom();

    Set<ProductoDTO> getAllProductos();

}

