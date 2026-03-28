package com.gamestack.inventario.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "videojuegos")
@Data
public class VideoJuego {

     @Id
    private int id;

     @Column(name = "titulo")
    private String titulo;

     @Column(name = "plataforma")
    private String plataforma;

     @Column(name = "precio")
    private double precio;

     @Column(name = "stock")
    private int stock;

     @Column(name = "fecha_lanzamiento")
    private Date fechaLanzamiento;

     public VideoJuego(){}
    public VideoJuego(int id, String titulo, String plataforma, double precio, int stock, Date fechaLanzamiento) {
        this.id = id;
        this.titulo = titulo;
        this.plataforma = plataforma;
        this.precio = precio;
        this.stock = stock;
        this.fechaLanzamiento = fechaLanzamiento;
    }
}
