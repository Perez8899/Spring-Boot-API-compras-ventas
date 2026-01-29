package com.perez.compras_ventas.controllers;

import com.perez.compras_ventas.dto.request.NotaRequest;
import com.perez.compras_ventas.dto.response.ClienteResponse;
import com.perez.compras_ventas.dto.response.NotaResponse;
import com.perez.compras_ventas.services.NotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nota")
@RequiredArgsConstructor
public class NotesController {

    private final NotaService notaService;

    @GetMapping
    public ResponseEntity<List<NotaResponse>> findAllNotas() {
        return ResponseEntity.ok().body(notaService.findAllNotas());
    }

    @GetMapping("/cliente")
    public ResponseEntity<List<ClienteResponse>> findAllClientes() {
        return ResponseEntity.ok().body(notaService.findAllClientes());
    }

    @PostMapping
    public ResponseEntity<NotaResponse> createNota(@RequestBody NotaRequest notaRequest) {
        return ResponseEntity.ok().body(notaService.createNota(notaRequest));
    }

}
