package com.perez.compras_ventas.feign.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {
    private String id;

    private String notificationIdentifier;

    private String usuario;

    private String titulo;

    private String body;

    private String category;

    private String prioridad;

    private String estado;

    private LocalDateTime readAt;

    private LocalDateTime fechaRegistro;
}