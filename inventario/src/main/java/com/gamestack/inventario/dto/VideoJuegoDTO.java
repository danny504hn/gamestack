package com.gamestack.inventario.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Data
@NoArgsConstructor
public class VideoJuegoDTO {

    private int id;
    @Schema(description = "Titulo principal del videojuego", example = "Age of Empires")
    @NotBlank
    @Size(min = 3, max = 100)
    private String titulo;

    @Schema(description = "Plataforma en la que se encuentra el videojuego", example = "PC")
    @NotBlank
    private String plataforma;

    @Schema(description = "Precio en euros del videojuego", example = "49.99")
    @Min(value=1)
    private double precio;

    @Schema(description = "Stock fisico del videojuego", example = "1000")
    @Min(value = 0)
    private int stock;

    @Schema(description = "Fecha de lanzamiento del videojuego", example = "1-1-2001")
    @NotNull
    private LocalDateTime fechaLanzamiento;

    public VideoJuegoDTO(int id, String titulo, String plataforma, double precio, int stock, LocalDateTime fechaLanzamiento) {
        this.id = id;
        this.titulo = titulo;
        this.plataforma = plataforma;
        this.precio = precio;
        this.stock = stock;
        this.fechaLanzamiento = fechaLanzamiento;
    }
}
