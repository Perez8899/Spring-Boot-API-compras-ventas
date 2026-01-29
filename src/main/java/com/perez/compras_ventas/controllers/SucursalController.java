package com.perez.compras_ventas.controllers;

import com.perez.compras_ventas.dto.response.AlmacenResponse;
import com.perez.compras_ventas.entity.Sucursal;
import com.perez.compras_ventas.services.SucursalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sucursal")
@RequiredArgsConstructor
public class SucursalController {

    private final SucursalService sucursalService;

    @GetMapping
    public ResponseEntity<List<Sucursal>> findAllSucursal() {
        return ResponseEntity.ok().body(sucursalService.findAllSucursal());
    }

    @GetMapping("/almacen/{id}")
    public ResponseEntity<List<AlmacenResponse>> findAllAlmacenBySucursal(@PathVariable Integer id) {
        return ResponseEntity.ok().body(sucursalService.findAlmacenesBySucursal(id));
    }
}
