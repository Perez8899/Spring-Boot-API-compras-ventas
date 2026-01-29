package com.perez.compras_ventas.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_prod")
    private Integer id;

     @Column(length = 100, nullable = false)
    private String nombre;
    
  
    private String descripcion;

    @Column(name = "codigo_barra", length = 100)
    private String codigoBarra;
   
    @Column(length = 100)
    private String marca;
    
    @Column(nullable = false, name = "precio_venta", precision = 10, scale = 2)
    private BigDecimal precioVenta;

    private String imagen;
    
    @Column(nullable = false)
    private Boolean estado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_categ")
    private Categoria categoria;
    
}