package com.perez.compras_ventas.dto.request;

import com.perez.compras_ventas.entity.Nota;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotaRequest {

    private String tipoNota;
    private BigDecimal descuento;
    private BigDecimal totalCalculado;
    private String observacion;
    private Integer usuId;
    private Integer clientId;
    private List<MovimientoRequest> movimientos;

    public static Nota toEntity(NotaRequest notaRequest){
        return Nota.builder()
                .descuento(notaRequest.getDescuento())
                .tipoNota(notaRequest.getTipoNota())
                .totalCalculado(notaRequest.getTotalCalculado())
                .observacion(notaRequest.getObservacion())
                .build();
    }
}
