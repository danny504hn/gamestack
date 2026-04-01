package com.gamestack.inventario.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockRequestDTO {

    @Schema(description = "Cantidad a restar del stock o sumar puede ser un valor positivo (add) o negativo (restar)", example = "-2")
    @NotNull
    private Integer cantidad;

    @Schema(description = "Motivo por el que se envia el stock", example = "VENTA")
    @NotBlank
    private String motivo;
}
