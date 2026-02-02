package com.perez.compras_ventas.dto.response;

import com.perez.compras_ventas.entity.Almacen;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlmacenResponse {

    private  Integer id;
     private String nombre;
     private String descripcion;
     private String codigo;

    public static AlmacenResponse fromEntity(Almacen almacen){
        return AlmacenResponse.builder()
                .id(almacen.getId())
                .nombre(almacen.getNombre())
                .descripcion(almacen.getDescripcion())
                .codigo(almacen.getCodigo())
                .build();
    }
}
