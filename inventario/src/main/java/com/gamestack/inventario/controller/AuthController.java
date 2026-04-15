package com.gamestack.inventario.controller;

import com.gamestack.inventario.dto.LoginDTO;
import com.gamestack.inventario.dto.RegistroDTO;
import com.gamestack.inventario.dto.TokenResponseDTO;
import com.gamestack.inventario.dto.UsuarioDTO;
import com.gamestack.inventario.security.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService service;

    @PostMapping("/registro")
    public ResponseEntity<UsuarioDTO> register(@RequestBody @Valid RegistroDTO user){
        return new ResponseEntity<>(service.registrar(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid LoginDTO user){
        return ResponseEntity.ok(service.login(user));
    }
}
