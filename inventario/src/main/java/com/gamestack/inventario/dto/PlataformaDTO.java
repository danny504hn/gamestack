package com.gamestack.inventario.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

@Data
@NoArgsConstructor
public class PlataformaDTO {

    private int id;

    @Schema(description = "Nombre de la plataforma",example = "PC")
    @NotBlank
    private String nombre;

    @Schema(description = "Descripcion de la plataforma", example = "Juegos que se encuentran unicamente en consola")
    private String description;

    public PlataformaDTO(String nombre, String description) {
        this.nombre = nombre;
        this.description = description;
    }
}
