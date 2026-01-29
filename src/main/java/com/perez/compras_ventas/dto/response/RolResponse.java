package com.perez.compras_ventas.dto.response;

import com.perez.compras_ventas.entity.Rol;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolResponse {
    private Integer id;
    private String nombre;
    private String descripcion;
    private List<Integer> permisos;

    public static RolResponse fromEntity(Rol rol, List<Integer> permisosIds) {
        return RolResponse.builder()
                .descripcion(rol.getDescripcion())
                .nombre(rol.getNombre())
                .permisos(permisosIds)
                .build();
    }
}
