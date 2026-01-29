package com.perez.compras_ventas.services;

import com.perez.compras_ventas.dto.request.NotaRequest;
import com.perez.compras_ventas.dto.response.ClienteResponse;
import com.perez.compras_ventas.dto.response.NotaResponse;

import java.util.List;

public interface NotaService {
    List<NotaResponse> findAllNotas();
    NotaResponse createNota(NotaRequest notaRequest);
    List<ClienteResponse> findAllClientes();
}
