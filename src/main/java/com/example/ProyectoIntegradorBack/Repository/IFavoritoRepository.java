package com.example.ProyectoIntegradorBack.Repository;

import com.example.ProyectoIntegradorBack.Model.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IFavoritoRepository extends JpaRepository<Favorito, Integer> {
    Optional<Favorito> findByClienteIdAndProductoId(Integer cliente_id, Integer producto_id);

    Optional<Favorito> findByClienteId(Integer clienteId);
}
