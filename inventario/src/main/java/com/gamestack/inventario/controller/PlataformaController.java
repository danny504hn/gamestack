package com.gamestack.inventario.controller;

import com.gamestack.inventario.dto.PlataformaDTO;
import com.gamestack.inventario.model.Plataforma;
import com.gamestack.inventario.service.PlataformaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/plataformas")
@RequiredArgsConstructor
public class PlataformaController {


    private final PlataformaService service;

    @PostMapping
    public ResponseEntity<PlataformaDTO> createPlataforma(@Valid @RequestBody PlataformaDTO nombre){
        PlataformaDTO newPlataforma = service.createPlataforma(nombre);
        return new ResponseEntity<>(newPlataforma,HttpStatus.CREATED);
    }
}
