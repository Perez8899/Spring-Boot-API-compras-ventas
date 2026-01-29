package com.perez.compras_ventas.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductAlmacenRequest {
    private Integer almacenId;
    private Integer productoId;
    private Integer stock;
}
