package com.example.ProyectoIntegradorBack.Service;

import com.example.ProyectoIntegradorBack.Model.ProductoDTO;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public interface IProductoService {
    void registrarProducto(ProductoDTO productoDTO);
    ProductoDTO verProducto(int id);
    void modificarProducto(ProductoDTO productoDTO);
    void eliminarProducto(int id);
    Set<ProductoDTO> listarProductos();
}

