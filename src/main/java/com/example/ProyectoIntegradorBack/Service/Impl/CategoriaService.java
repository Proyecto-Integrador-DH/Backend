package com.example.ProyectoIntegradorBack.Service.Impl;

import com.example.ProyectoIntegradorBack.Model.Categoria;
import com.example.ProyectoIntegradorBack.Model.DTOs.CategoriaDTO;
import com.example.ProyectoIntegradorBack.Model.DTOs.CategoriaNombreDTO;
import com.example.ProyectoIntegradorBack.Model.DTOs.ImagenDTO;
import com.example.ProyectoIntegradorBack.Model.DTOs.ProductoDTO;
import com.example.ProyectoIntegradorBack.Repository.ICategoriaRepository;
import com.example.ProyectoIntegradorBack.Service.ICategoriaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaService implements ICategoriaService {
    @Autowired
    private ICategoriaRepository categoriaRepository;
    @Autowired
    ObjectMapper mapper;
    @Override
    public CategoriaDTO postCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = mapper.convertValue(categoriaDTO, Categoria.class);
        categoriaRepository.save(categoria);
        return mapper.convertValue(categoria, CategoriaDTO.class);
    }

    @Override
    public CategoriaDTO getCategoria(Integer id) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
        CategoriaDTO categoriaDTO = null;
        if (optionalCategoria.isPresent()) {
            Categoria categoria = optionalCategoria.get();
            categoriaDTO = convertToDto(categoria);
        }
        return categoriaDTO;
    }

    @Override
    public void updateCategoria(CategoriaDTO categoriaDTO) {

    }

    @Override
    public void deleteCategoria(Integer id) {

    }

    @Override
    public List<CategoriaNombreDTO> getAllCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        List<CategoriaNombreDTO> categoriasDTO = new ArrayList<>();
        for (Categoria categoria : categorias) {
            CategoriaNombreDTO categoriaNombreDTO = mapper.convertValue(categoria, CategoriaNombreDTO.class);
            categoriasDTO.add(categoriaNombreDTO);
        }
        return categoriasDTO;
    }

    private CategoriaDTO convertToDto(Categoria categoria) {
        List<ProductoDTO> productosDto = categoria.getProductos().stream()
                .map(producto -> new ProductoDTO(
                        producto.getNombre(),
                        producto.getDescripcion(),
                        producto.getFecha(),
                        producto.getCupo(),
                        producto.isDisponible(),
                        producto.getImagenes().stream()
                                .map(imagen -> new ImagenDTO(imagen.getUrl(), imagen.getAltText()))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());

        CategoriaDTO dto = new CategoriaDTO(
                categoria.getNombre(),
                productosDto
        );
        return dto;
    }
}