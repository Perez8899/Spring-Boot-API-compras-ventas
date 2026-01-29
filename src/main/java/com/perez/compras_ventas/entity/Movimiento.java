package com.perez.compras_ventas.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Movimiento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_mov")
    private Integer idMov;

    @Column(nullable = false)
    private Integer cantidad;

     @Column(nullable = false,  precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    private String observacion;

    @Column(nullable = false, length = 20)
    private String tipoMovimiento;


   @ManyToOne
    @JoinColumn(name = "id_nota")
    private Nota nota;
    
    @ManyToOne
    @JoinColumn(name = "id_prod")
    private Producto producto;
    
    @ManyToOne
    @JoinColumn(name = "id_alm")
    private Almacen almacen;
}
