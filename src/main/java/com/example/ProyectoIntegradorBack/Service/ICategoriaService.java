package com.example.ProyectoIntegradorBack.Service;

import com.example.ProyectoIntegradorBack.Model.DTOs.CategoriaDTO;
import com.example.ProyectoIntegradorBack.Model.DTOs.CategoriaNombreDTO;

import java.util.List;

public interface ICategoriaService {
    CategoriaDTO postCategoria(CategoriaDTO categoriaDTO);
    CategoriaDTO getCategoria(Integer id);
    void updateCategoria(CategoriaDTO categoriaDTO);
    void deleteCategoria(Integer id);
    List<CategoriaNombreDTO> getAllCategorias();

}
