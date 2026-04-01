package com.gamestack.inventario.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Entity
@Table(name = "movimiento_stock")
@Data
public class MovimientoStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "motivo")
    private String motivo;

    @ManyToOne(fetch = FetchType.LAZY,optional = false )
    @JoinColumn(name = "id_videojuego",nullable = false)
    private VideoJuego videojuego;

    public MovimientoStock(){}

    public MovimientoStock(int cantidad, LocalDateTime fecha, String motivo) {
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.motivo = motivo;
    }
}
