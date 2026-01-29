package com.perez.compras_ventas.dto.request;

import com.perez.compras_ventas.entity.Producto;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

    private String nombre;
    private String descripcion;
    private String marca;
    private BigDecimal precioVenta;
    private MultipartFile file;
    private Integer categoriaId;

    public static Producto toEntity(ProductRequest productoRequest){
        return Producto.builder()
                .nombre(productoRequest.getNombre())
                .descripcion(productoRequest.getDescripcion())
                .marca(productoRequest.getMarca())
                .precioVenta(productoRequest.getPrecioVenta())
                .build();
    }
}
