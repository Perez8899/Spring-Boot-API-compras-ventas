package com.perez.compras_ventas.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class AlmacenProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_alm_prod")
    private Integer id;
    
    @Column(nullable=false)
    private Integer stock; 
    

    private LocalDateTime fechaActualizacion; 
    
    @ManyToOne
    @JoinColumn(name = "id")
    private Almacen almacen;
    
    @ManyToOne
    @JoinColumn(name = "id_prod") 
    private Producto producto;
}