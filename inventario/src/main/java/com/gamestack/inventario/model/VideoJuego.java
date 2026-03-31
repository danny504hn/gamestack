package com.gamestack.inventario.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "videojuegos")
@Data
public class VideoJuego {

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

     @Column(name = "titulo")
    private String titulo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plataforma" ,nullable = false)
    private Plataforma plataforma;

     @Column(name = "precio")
    private double precio;

     @Column(name = "stock")
    private int stock;

     @Column(name = "fecha_lanzamiento")
    private Date fechaLanzamiento;

     public VideoJuego(){}
    public VideoJuego( String titulo, Plataforma plataforma, double precio, int stock, Date fechaLanzamiento) {
        this.titulo = titulo;
        this.plataforma = plataforma;
        this.precio = precio;
        this.stock = stock;
        this.fechaLanzamiento = fechaLanzamiento;
    }
}
