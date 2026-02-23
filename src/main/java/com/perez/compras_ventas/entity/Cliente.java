package com.perez.compras_ventas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cliente {

    @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_clien")
    private Integer idClien;

    private String tipo;

     @Column(nullable = false, name = "razon_social", length = 100)
    private String razonSocial;

    @Column(name = "nro_identificacion", length = 100)
    private String nroIdentificacion;

    @Column(nullable = false,length = 20)
    private String telefono;

    private String direccion;

    @Column(nullable = false,length = 100)
    private String correo;

     @Column(nullable = false)
    private Boolean estado;
    
}
