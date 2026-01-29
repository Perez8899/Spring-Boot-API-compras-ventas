package com.perez.compras_ventas.dto.response;

import com.perez.compras_ventas.entity.Nota;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotaResponse
{
    private Integer id;
    private LocalDateTime fechaRegistro;
    private String observacion;
    private String tipoNota;
    private BigDecimal totalCalculado;
    private BigDecimal descuento;

    public static NotaResponse fromEntity(Nota nota){
        return NotaResponse.builder()
                .id(nota.getIdNota())
                .fechaRegistro(nota.getFecha())
                .observacion(nota.getObservacion())
                .tipoNota(nota.getTipoNota())
                .descuento(nota.getDescuento())
                .totalCalculado(nota.getTotalCalculado())
                .build();
    }

}
