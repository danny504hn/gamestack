package com.gamestack.inventario.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "plataformas")
@Data
public class Plataforma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "plataforma",cascade = CascadeType.ALL)
    private List<VideoJuego> videoJuegos;

    public Plataforma(){}

    public Plataforma(String nombre, String description) {
        this.nombre = nombre;
        this.description = description;
    }
}
