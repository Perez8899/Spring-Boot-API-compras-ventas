package com.perez.compras_ventas.repository;

import com.perez.compras_ventas.entity.AlmacenProducto;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface AlmacenProductRepository extends ListCrudRepository<AlmacenProducto, Integer> {

    List<AlmacenProducto> findByAlmacen_Id(Integer almacenId);
    Optional<AlmacenProducto> findByAlmacen_IdAndProducto_IdProducto(Integer almacenId, Integer productoId);
}
