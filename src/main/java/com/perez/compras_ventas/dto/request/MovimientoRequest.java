package com.perez.compras_ventas.dto.request;

import com.perez.compras_ventas.entity.Movimiento;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimientoRequest {

    private Integer cantidad;
    private BigDecimal precioUnitario;
    private String observacion;
    private String tipoMovimiento;
    private Integer notaId;
    private Integer productoId;
    private Integer almacenId;

    public static Movimiento toEntity(MovimientoRequest movimientoRequest){
        return Movimiento.builder()
                .cantidad(movimientoRequest.getCantidad())
                .tipoMovimiento(movimientoRequest.getTipoMovimiento())
                .observacion(movimientoRequest.getObservacion())
                .precioUnitario(movimientoRequest.getPrecioUnitario())
                .build();
    }
}
