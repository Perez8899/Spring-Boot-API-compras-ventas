package com.perez.compras_ventas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class PermisoRol {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_per_rol")
    private Integer idPerRol;

    @ManyToOne
    @JoinColumn(name = "id_per")
    private Permiso permiso;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;
}
