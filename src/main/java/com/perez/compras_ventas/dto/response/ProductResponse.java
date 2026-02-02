package com.perez.compras_ventas.dto.response;

import com.perez.compras_ventas.entity.Producto;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private Integer id;
    private String nombre;
    private String descripcion;
    private String codigoBarra;
    private String marca;
    private BigDecimal precioVenta;
    private String nombreCategoria;

    public static ProductResponse fromEntity(Producto producto){
        return ProductResponse.builder()
                .id(producto.getIdProducto())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .codigoBarra(producto.getCodigoBarra())
                .marca(producto.getMarca())
                .precioVenta(producto.getPrecioVenta())
                .nombreCategoria(producto.getCategoria().getNombre())
                .build();
    }
}
