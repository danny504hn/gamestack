package com.gamestack.inventario.controller;

import com.gamestack.inventario.dto.VideoJuegoDTO;
import com.gamestack.inventario.model.VideoJuego;
import com.gamestack.inventario.service.VideoJuegoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/videojuegos")
public class VideoJuegoController {

    @Autowired
    private VideoJuegoService service;



    @GetMapping("/plataforma/{nombre}")
    public ResponseEntity<List<VideoJuegoDTO>> getByPlataforma(@PathVariable String nombre){
        ResponseEntity <List<VideoJuegoDTO>> respuesta = ResponseEntity.notFound().build();
        List<VideoJuegoDTO> target = service.getByPlataforma(nombre);
        if(!target.isEmpty()){
            respuesta = ResponseEntity.ok(target);
        }
        return respuesta;
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoJuegoDTO> getById(@PathVariable int id){
        ResponseEntity<VideoJuegoDTO> respuesta = ResponseEntity.notFound().build();
        VideoJuegoDTO target = service.getById(id);
        return respuesta = ResponseEntity.ok(target);
    }

    @GetMapping
    public ResponseEntity<List<VideoJuegoDTO>> getAllVideoJuegos(){
        List<VideoJuegoDTO> juegos = service.getAll();
        ResponseEntity<List<VideoJuegoDTO>> respuesta = ResponseEntity.notFound().build();
        if(!juegos.isEmpty()){
            respuesta = ResponseEntity.ok(juegos);
        }
        return respuesta;
    }

    @PostMapping
    public ResponseEntity<VideoJuegoDTO> createVideoJuego(@Valid @RequestBody VideoJuegoDTO game){

        VideoJuegoDTO newVideoJuego = service.createVideojuego(game);
        return new ResponseEntity<>(newVideoJuego, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteVideoJuego(@PathVariable int id){
        boolean deleted = service.deleteById(id);
        ResponseEntity<Boolean> respuesta = ResponseEntity.notFound().build();
        if(deleted){
            respuesta = ResponseEntity.noContent().build();
        }
        return respuesta;
    }
}
