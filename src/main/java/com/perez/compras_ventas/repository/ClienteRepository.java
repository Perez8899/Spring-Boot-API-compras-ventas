package com.perez.compras_ventas.repository;

import com.perez.compras_ventas.entity.Cliente;
import org.springframework.data.repository.ListCrudRepository;

public interface ClienteRepository extends ListCrudRepository<Cliente, Integer> {
}
