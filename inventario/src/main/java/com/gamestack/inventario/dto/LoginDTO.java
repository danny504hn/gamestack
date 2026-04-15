package com.gamestack.inventario.dto;

import com.gamestack.inventario.enums.Rol;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class LoginDTO {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
