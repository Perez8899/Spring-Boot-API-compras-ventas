package com.perez.compras_ventas.dto.response;

import com.perez.compras_ventas.entity.Usuario;
import lombok.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponse {
    
    private Integer idUsu;

    private String nombre;

    private String apellido;

    private String  fechaNacimiento;

    private String telefono;

    private String direccion;

    private String dni;

    private String correo;

    private String userName;

    private List<Integer> roles;

    public static UsuarioResponse fromEntity(Usuario usuario){ 
        return UsuarioResponse.builder()
        .idUsu(usuario.getIdUsu())   
        .nombre(usuario.getNombre())
        .apellido(usuario.getApellido())
        .fechaNacimiento(usuario.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
        .telefono(usuario.getTelefono())
        .direccion(usuario.getDireccion())
        .dni(usuario.getDni())
        .correo(usuario.getCorreo())
        .userName(usuario.getUserName())
        .roles(usuario.getRoles().stream().map(rol->rol.getIdRol()).collect(Collectors.toList()))

        .build();
    }
}
