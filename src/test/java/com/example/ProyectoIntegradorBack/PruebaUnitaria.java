package com.example.ProyectoIntegradorBack;

import com.example.ProyectoIntegradorBack.Model.Producto;
import com.example.ProyectoIntegradorBack.Model.ProductoDTO;
import com.example.ProyectoIntegradorBack.Repository.IProductoRepository;
import com.example.ProyectoIntegradorBack.Service.IProductoService;
import com.example.ProyectoIntegradorBack.Service.ProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PruebaUnitaria {

    private IProductoRepository mockProductoRepository;
    private ObjectMapper mockMapper;
    private ProductoService productoService;

    @Before
    public void setUp() {
        mockProductoRepository = mock(IProductoRepository.class);
        mockMapper = mock(ObjectMapper.class);
        productoService = new ProductoService();

        productoService.setProductoRepository(mockProductoRepository);
        productoService.setMapper(mockMapper);

        when(mockMapper.convertValue(any(), eq(ProductoDTO.class)))
                .thenAnswer(invocation -> {
                    Object source = invocation.getArgument(0);
                    if (source instanceof Producto) {
                        Producto producto = (Producto) source;
                        return new ProductoDTO(producto.getId(), producto.getName(), producto.getDescription(), producto.getImagesId());
                    }
                    return invocation.callRealMethod(); // Llamada al método real si no es una conversión de Producto a ProductoDTO
                });
    }

    @Test
    public void testListarProductos() {
        List<Producto> productos = crearProductosDePrueba();
        when(mockProductoRepository.findAll()).thenReturn(productos);

        for (Producto producto : productos) {
            when(mockMapper.convertValue(eq(producto), eq(ProductoDTO.class))).thenReturn(
                    new ProductoDTO(producto.getId(), producto.getName(), producto.getDescription(), producto.getImagesId()));
        }

        Set<ProductoDTO> resultado = productoService.listarProductos();

        assertEquals(10, resultado.size());

        verify(mockProductoRepository, times(1)).findAll();

        for (Producto producto : productos) {
            verify(mockMapper, times(1)).convertValue(eq(producto), eq(ProductoDTO.class));
        }
    }

    private List<Producto> crearProductosDePrueba() {
        List<Producto> productos = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Producto producto = new Producto();
            producto.setId(i);
            producto.setName("Producto " + i);
            producto.setDescription("Descripción del producto " + i);
            producto.setDate(new Date());
            producto.setAvailability(10);

            productos.add(producto);
        }

        return productos;
    }

    @Test
    public void testVerProductoExistente() {
        int productId = 1;
        Producto productoMock = new Producto(productId, "NombreProducto", "DescripciónProducto", new Date(), 1, 1);
        when(mockProductoRepository.findById(productId)).thenReturn(Optional.of(productoMock));

        ProductoDTO productoDtoMock = new ProductoDTO(productId, "NombreProducto", "DescripciónProducto", 1);
        when(mockMapper.convertValue(productoMock, ProductoDTO.class)).thenReturn(productoDtoMock);

        ProductoDTO resultado = productoService.verProducto(productId);

        assertEquals(productoDtoMock, resultado);

        verify(mockProductoRepository, times(1)).findById(productId);
        verify(mockMapper, times(1)).convertValue(productoMock, ProductoDTO.class);
    }

    @Test
    public void testVerProductoNoExistente() {
        int productId = 1;
        when(mockProductoRepository.findById(productId)).thenReturn(Optional.empty());

        ProductoDTO resultado = productoService.verProducto(productId);

        assertNull(resultado);

        verify(mockProductoRepository, times(1)).findById(productId);
        verify(mockMapper, never()).convertValue(any(), eq(ProductoDTO.class));
    }
}


