package com.perez.compras_ventas.services;

import com.perez.compras_ventas.dto.response.AlmacenResponse;
import com.perez.compras_ventas.entity.Sucursal;
import com.perez.compras_ventas.repository.AlmacenRepository;
import com.perez.compras_ventas.repository.SucursalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SucursalServiceImpl implements SucursalService {
    private final SucursalRepository sucursalRepository;

    private final AlmacenRepository almacenRepository;

    @Override
    public List<Sucursal> findAllSucursal() {
        return sucursalRepository.findAll();
    }

    @Override
    public List<AlmacenResponse> findAlmacenesBySucursal(Integer sucursalId) {
        return almacenRepository.findBySucursal_IdSuc(sucursalId).stream()
                .map(AlmacenResponse::fromEntity).collect(Collectors.toList());
    }
}
