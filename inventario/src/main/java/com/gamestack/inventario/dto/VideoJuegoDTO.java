package com.gamestack.inventario.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class VideoJuegoDTO {

    private int id;
    @Schema(description = "Titulo principal del videojuego", example = "Age of Empires")
    @NotBlank
    @Size(min = 3, max = 100)
    private String titulo;

    @Schema(description = "Plataforma en la que se encuentra el videojuego", example = "1")
    @NotNull
    private Integer plataformaId;

    private String plataformaNombre;

    @Schema(description = "Precio en euros del videojuego", example = "49.99")
    @Min(value=1)
    private double precio;

    @Schema(description = "Stock fisico del videojuego", example = "1000")
    @Min(value = 0)
    private int stock;

    @Schema(description = "Fecha de lanzamiento del videojuego", example = "1-1-2001")
    @NotNull
    private LocalDateTime fechaLanzamiento;

    public VideoJuegoDTO(String titulo, Integer plataformaId, String plataformaNombre, double precio, int stock, LocalDateTime fechaLanzamiento) {
        this.titulo = titulo;
        this.plataformaId = plataformaId;
        this.plataformaNombre = plataformaNombre;
        this.precio = precio;
        this.stock = stock;
        this.fechaLanzamiento = fechaLanzamiento;
    }
}
