package com.example.ProyectoIntegradorBack.Service;

import com.example.ProyectoIntegradorBack.Model.DTOs.ProductoDTO;

import java.util.Set;

public interface IProductoService {
    ProductoDTO postProducto(ProductoDTO productoDTO);
    ProductoDTO getProductoDTO(Integer id);

    void updateProducto(ProductoDTO productoDTO);
    void deleteProducto(Integer id);
    Set<ProductoDTO> getAllProductos();

}

