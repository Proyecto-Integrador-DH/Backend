package com.example.ProyectoIntegradorBack.Service;

import com.example.ProyectoIntegradorBack.Model.ProductoDTO;

import java.util.Set;

public interface IProductoService {
    ProductoDTO registrarProducto(ProductoDTO productoDTO);
    ProductoDTO verProducto(Integer id);
    void modificarProducto(ProductoDTO productoDTO);
    void eliminarProducto(Integer id);
    Set<ProductoDTO> listarTodos();
}

