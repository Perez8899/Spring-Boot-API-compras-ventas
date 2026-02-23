package com.perez.compras_ventas.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_usu")
    private Integer idUsu;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;


    private LocalDate fechaNacimiento;

    @Column(length = 20)
    private String telefono;

    private String direccion;

    @Column(nullable = false, unique = true, length = 20)
    private String dni;

    @Column(length = 20, unique = true)
    private String userName;

    @Column(nullable = false, length = 100, unique = true)
    private String correo;

    @Column(nullable = false, length = 60)
    private String password;

    private String estado;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable( // no se crea la tabla UsuarioRol con @ManyToMany
            name = "usuario_rol", joinColumns = @JoinColumn(name = "id_usu"), inverseJoinColumns = @JoinColumn(name = "id_rol"))
    private List<Rol> roles;

}
