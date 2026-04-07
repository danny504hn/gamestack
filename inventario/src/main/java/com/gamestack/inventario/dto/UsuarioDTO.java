package com.gamestack.inventario.dto;

import com.gamestack.inventario.enums.Rol;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDTO {

    @Schema(description = "Id del usuario", example = "1")
    @NotNull
    private int id;

    @Schema(description = "Nombre del usuario", example = "danny")
    @NotNull
    private String username;

    @Schema(description = "Rol del usuario", example = "USER")
    private Rol rol;
}
