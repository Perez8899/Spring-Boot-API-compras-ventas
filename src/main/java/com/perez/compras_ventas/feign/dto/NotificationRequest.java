package com.perez.compras_ventas.feign.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationRequest {
    private String usuario;

    private String titulo;

    private String body;

    private String category;

    private String prioridad;
}