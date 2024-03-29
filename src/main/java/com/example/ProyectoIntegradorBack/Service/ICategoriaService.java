package com.example.ProyectoIntegradorBack.Service;

import com.example.ProyectoIntegradorBack.Model.DTOs.CategoriaDTO;
import com.example.ProyectoIntegradorBack.Model.DTOs.CategoriaNombreDTO;

import java.util.Collection;
import java.util.List;

public interface ICategoriaService {
    CategoriaDTO postCategoria(CategoriaDTO categoriaDTO);
    CategoriaNombreDTO getCategoria(Integer id);
    void updateCategoria(CategoriaDTO categoriaDTO);
    void deleteCategoria(Integer id);
    List<CategoriaNombreDTO> getAllCategorias();
    Collection<CategoriaDTO> getCategoryProducts(Integer id);
}
