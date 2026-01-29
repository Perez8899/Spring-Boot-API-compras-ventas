package com.perez.compras_ventas.services;

import com.perez.compras_ventas.dto.response.AlmacenResponse;
import com.perez.compras_ventas.entity.Sucursal;

import java.util.List;

public interface SucursalService {
    List<Sucursal> findAllSucursal();

    List<AlmacenResponse> findAlmacenesBySucursal(Integer sucursalId);
}
