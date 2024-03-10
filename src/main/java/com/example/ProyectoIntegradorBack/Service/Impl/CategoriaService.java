package com.example.ProyectoIntegradorBack.Service.Impl;

import com.example.ProyectoIntegradorBack.Model.Categoria;
import com.example.ProyectoIntegradorBack.Model.DTOs.*;
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
    public CategoriaNombreDTO getCategoria(Integer id) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
        CategoriaNombreDTO categoriaNombreDTO = null;
        if (optionalCategoria.isPresent()) {
            Categoria categoria = optionalCategoria.get();
            categoriaNombreDTO = mapper.convertValue(categoria, CategoriaNombreDTO.class);
        }
        return categoriaNombreDTO;
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

    @Override
    public List<CategoriaDTO> getCategoryProducts(Integer id) {
        List<CategoriaDTO> categoriasDTO = categoriaRepository.findById(id).stream()
                .map(categoria -> new CategoriaDTO(
                        categoria.getNombre(),
                        categoria.getProductos().stream()
                                .map(producto -> new ProductoDTOCat(
                                        producto.getId(),
                                        producto.getNombre(),
                                        producto.getDescripcion(),
                                        producto.getFecha(),
                                        producto.getCupo(),
                                        producto.isDisponible(),
                                        producto.getImagenes().stream()
                                                .map(imagen -> new ImagenDTO(imagen.getUrl(), imagen.getAltText()))
                                                .collect(Collectors.toList())
                                ))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
        return categoriasDTO;
    }
}
