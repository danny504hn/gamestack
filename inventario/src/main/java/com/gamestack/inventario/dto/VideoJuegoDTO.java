package com.gamestack.inventario.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
@Data
public class VideoJuegoDTO {

    private int id;

    @NotBlank
    @Size(min = 3)
    private String titulo;
    @NotBlank
    private String plataforma;
    @Positive
    private double precio;
    @Min(value = 0)
    private int stock;

    private Date fechaLanzamiento;

    public VideoJuegoDTO(int id, String titulo, String plataforma, double precio, int stock, Date fechaLanzamiento) {
        this.id = id;
        this.titulo = titulo;
        this.plataforma = plataforma;
        this.precio = precio;
        this.stock = stock;
        this.fechaLanzamiento = fechaLanzamiento;
    }
}
