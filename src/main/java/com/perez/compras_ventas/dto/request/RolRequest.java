package com.perez.compras_ventas.dto.request;

import com.perez.compras_ventas.entity.Rol;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolRequest {
    private String nombre;
    private String descripcion;
    private List<Integer> permisos;

    public static Rol toEntity(RolRequest rolRequest){
        return Rol.builder()
                .descripcion(rolRequest.getDescripcion())
                .nombre(rolRequest.getNombre())
                .build();
    }
}
