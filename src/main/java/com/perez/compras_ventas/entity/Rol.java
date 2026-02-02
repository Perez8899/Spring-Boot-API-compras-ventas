package com.perez.compras_ventas.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Rol {  // 1

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Integer idRol;

    @Column(nullable = false, unique = true, length = 50)
    private String nombre;

    @Column()
    private String descripcion;


    @ManyToMany(mappedBy="roles", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Usuario> usuarios;
}
