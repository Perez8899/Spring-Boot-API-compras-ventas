package com.perez.compras_ventas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "almacen")
public class Almacen {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String nombre;

    private String descripcion;
   
    @Column(length = 20)
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "id_usu")
    private Sucursal sucursal;

}