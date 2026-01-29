package com.perez.compras_ventas.dto.response;

import com.perez.compras_ventas.entity.Cliente;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteResponse {

    private Integer id;
    private String razonSocial;
    private String nroIdentificacion;

    public static ClienteResponse fromEntity(Cliente cliente) {
        return ClienteResponse.builder()
                .id(cliente.getIdClien())
                .nroIdentificacion(cliente.getNroIdentificacion())
                .razonSocial(cliente.getRazonSocial())
                .build();
    }

}
