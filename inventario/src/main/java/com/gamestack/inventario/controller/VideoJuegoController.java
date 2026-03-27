package com.gamestack.inventario.controller;

import com.gamestack.inventario.model.VideoJuego;
import com.gamestack.inventario.service.VideoJuegoService;
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
    public ResponseEntity<List<VideoJuego>> getByPlataforma(@PathVariable String nombre){
        ResponseEntity <List<VideoJuego>> respuesta = ResponseEntity.notFound().build();
        List<VideoJuego> target = service.getByPlataforma(nombre);
        if(!target.isEmpty()){
            respuesta = ResponseEntity.ok(target);
        }
        return respuesta;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<VideoJuego>> getById(@PathVariable int id){
        ResponseEntity<Optional<VideoJuego>> respuesta = ResponseEntity.notFound().build();
        Optional<VideoJuego> target = service.getById(id);
        if(target.isPresent()){
            respuesta = ResponseEntity.ok(target);
        }
        return respuesta;
    }

    @GetMapping
    public ResponseEntity<List<VideoJuego>> getAllVideoJuegos(){
        List<VideoJuego> juegos = service.getAll();
        ResponseEntity<List<VideoJuego>> respuesta = ResponseEntity.notFound().build();
        if(!juegos.isEmpty()){
            respuesta = ResponseEntity.ok(juegos);
        }
        return respuesta;
    }

    @PostMapping
    public ResponseEntity<VideoJuego> createVideoJuego(@RequestBody VideoJuego game){
        VideoJuego newVideoJuego = service.createVideojuego(game);
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
