package com.gamestack.inventario.controller;

import com.gamestack.inventario.dto.VideoJuegoDTO;
import com.gamestack.inventario.model.Plataforma;
import com.gamestack.inventario.service.PlataformaService;
import com.gamestack.inventario.service.VideoJuegoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videojuegos")
@RequiredArgsConstructor
public class VideoJuegoController {
    private final VideoJuegoService service;

    @Operation(summary = "Obtener la lista de videojuegos segun la plataforma",description = "Recupera todos los videojuegos de X plataforma, si no existe devuelve  un 404")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200",description = "Plataforma encontrada, o contiene videojuegos"),
            @ApiResponse(responseCode = "404",description = "No se encontro la plataforma y la lista esta vacia")
    })
    @GetMapping("/plataforma/{nombre}")
    public ResponseEntity<List<VideoJuegoDTO>> getByPlataforma(@PathVariable String nombre){
        ResponseEntity <List<VideoJuegoDTO>> respuesta = ResponseEntity.notFound().build();
        List<VideoJuegoDTO> target = service.getByPlataforma(nombre);
        if(!target.isEmpty()){
            respuesta = ResponseEntity.ok(target);
        }
        return respuesta;
    }
    @Operation(summary = "Obtener un videojuego por su Id",description = "Recupera un videojuego por su ID, si no lo encuentra devuelve 404")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200",description = "Videojuego encontraoa"),
            @ApiResponse(responseCode = "404",description = "No se encontro el videojuego")
    })
    @GetMapping("/{id}")
    public ResponseEntity<VideoJuegoDTO> getById(@PathVariable int id){
        VideoJuegoDTO target = service.getById(id);
        return ResponseEntity.ok(target);
    }


    @Operation(summary = "Obten todos los videojuegos de la DB",description = "Recupera Todos los videojuegos si no los encuentra devuelve 404")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200",description = "Recupero todos los videojuegos"),
            @ApiResponse(responseCode = "404",description = "No se encontraron videojuegos")
    })
    @GetMapping
    public ResponseEntity<List<VideoJuegoDTO>> getAllVideoJuegos(){
        List<VideoJuegoDTO> juegos = service.getAll();
        ResponseEntity<List<VideoJuegoDTO>> respuesta = ResponseEntity.notFound().build();
        if(!juegos.isEmpty()){
            respuesta = ResponseEntity.ok(juegos);
        }
        return respuesta;
    }


    @Operation(summary = "Crea un videojuego en la DB",description = "Apartir del JSON enviado, construye un Videojuego y lo guarda en la DB, devuelve 201 si lo consigui")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "201",description = "Videojuego Creado"),
    })
    @PostMapping
    public ResponseEntity<VideoJuegoDTO> createVideoJuego(@Valid @RequestBody VideoJuegoDTO game){

        VideoJuegoDTO newVideoJuego = service.createVideojuego(game);
        return new ResponseEntity<>(newVideoJuego, HttpStatus.CREATED);
    }

    @Operation(summary = "Elimina un videojuego por su Id",description = "Elimina un videojuego por su ID, si no lo encuentra devuelve 404")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200",description = "Videojuego encontrado y eliminado"),
            @ApiResponse(responseCode = "404",description = "No se encontro el videojuego")
    })
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
