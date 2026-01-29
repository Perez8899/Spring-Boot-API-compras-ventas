package com.perez.compras_ventas.dto.request;

import com.perez.compras_ventas.entity.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioRequest {
    
    @NotBlank(message = "El nombre es obligatorio") // ""
    @Size(max = 100, message = "Maximo 100 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio") // ""
    @Size(max = 100, message = "Maximo 100 caracteres")
    private String apellido;

    @NotBlank(message = "El fecha es obligatorio")
    private String fechaNacimiento;

   
    @Size(max = 12, message = "Maximo 12 caracteres")
    private String telefono;

    @NotBlank(message = "El DIRECCION es obligatorio") // ""
    private String direccion;

    @NotBlank(message = "El DNI es obligatorio") // ""
    @Size(max = 20, message = "Maximo 20 caracteres")
    private String dni;

    @Email(message = "El correo debe tener formato valido")
    @NotBlank(message = "El CORREO es obligatorio") // ""
    @Size(max = 100, message = "Maximo 100 caracteres")
    private String correo;


    public static Usuario toEntity(UsuarioRequest usuarioRequest){
        return Usuario.builder()
               .nombre(usuarioRequest.getNombre())
               .apellido(usuarioRequest.getApellido())
               .fechaNacimiento(LocalDate.parse(usuarioRequest.getFechaNacimiento()))
               .telefono(usuarioRequest.getTelefono())
               .direccion(usuarioRequest.getDireccion())
               .dni(usuarioRequest.getDni())
               .correo(usuarioRequest.getCorreo())
               .build();
    }

}
