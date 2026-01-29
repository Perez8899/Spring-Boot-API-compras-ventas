package com.perez.compras_ventas.repository;

import com.perez.compras_ventas.entity.Almacen;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface AlmacenRepository extends ListCrudRepository<Almacen, Integer> {
    List<Almacen> findBySucursal_Id(Integer id);
}
