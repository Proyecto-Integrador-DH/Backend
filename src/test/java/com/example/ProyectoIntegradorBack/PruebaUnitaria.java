package com.example.ProyectoIntegradorBack;

import com.example.ProyectoIntegradorBack.Model.ProductoDTO;
import com.example.ProyectoIntegradorBack.Repository.IProductoRepository;
import com.example.ProyectoIntegradorBack.Service.IProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest

public class PruebaUnitaria {
    @Autowired
    private IProductoService productoService;
    @Autowired
    private IProductoRepository productoRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
        public void testListarProductos() {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(1);
        productoDTO.setName("producto1");
        productoDTO.setDescription("prueba1");
        productoDTO.setImagesId(1);

        ProductoDTO productoDTO2 = new ProductoDTO();
        productoDTO2.setId(2);
        productoDTO2.setName("producto2");
        productoDTO2.setDescription("prueba2");
        productoDTO2.setImagesId(2);
        productoService.listarProductos();

        Set<ProductoDTO> result = productoService.listarProductos();

        assertEquals(10, result.size());

        Set<Integer> idsElegidos = new HashSet<>();
        for (ProductoDTO producto : result) {
            Integer id = producto.getId();
            assertFalse(idsElegidos.contains(id), "Se encontró un producto duplicado");
            idsElegidos.add(id);
        }
    }
    }


