package com.example.ProyectoIntegradorBack.Service;

import com.example.ProyectoIntegradorBack.Model.DTOs.CategoriaDTO;
import com.example.ProyectoIntegradorBack.Model.DTOs.CategoriaNombreDTO;
import com.example.ProyectoIntegradorBack.Model.DTOs.NuevoProductoDTO;
import com.example.ProyectoIntegradorBack.Model.DTOs.ProductoDTO;
import com.example.ProyectoIntegradorBack.Model.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface IProductoService {
    Producto postProducto(NuevoProductoDTO productoDTO);
    ProductoDTO getProductoDTO(Integer id);

    void updateProducto(NuevoProductoDTO productoDTO);

    void updateProductoCaracteristicas(Integer idProducto, List<Integer> caracteristicas);
    void deleteProducto(Integer id);
    Set<ProductoDTO> getAllProductosRandom();

    Set<ProductoDTO> getAllProductos();

    void addCategoria(Integer idProducto, CategoriaNombreDTO categoriaDTO);

}

