package com.perez.compras_ventas.repository;

import com.perez.compras_ventas.entity.Categoria;
import org.springframework.data.repository.ListCrudRepository;

public interface CategoriaRepository extends ListCrudRepository<Categoria, Integer> {
}
