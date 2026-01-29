package com.perez.compras_ventas.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_nota")
    private Integer idNota;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(length = 20)
    private String tipoNota;

     @Column(precision = 10, scale = 2)
    private BigDecimal descuento;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalCalculado;

    private String observacion;


    @ManyToOne
    @JoinColumn(name = "id_usu")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_clien")
    private Cliente cliente;

}
