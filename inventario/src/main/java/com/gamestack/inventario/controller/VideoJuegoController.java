package com.gamestack.inventario.controller;

import com.gamestack.inventario.model.VideoJuego;
import com.gamestack.inventario.repository.VideoJuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videojuegos")
public class VideoJuegoController {

    @Autowired
    private VideoJuegoRepository repo;

    @GetMapping
    public List<VideoJuego> getAllVideoJuegos(){
        return repo.findAll();
    }

    @PostMapping
    public void SaveJsonToDB(@RequestBody VideoJuego game){
        repo.save(game);
    }

    @DeleteMapping("/{id}")
    public void DeleteVideoJuego(@PathVariable int id){
        repo.deleteById(id);
    }
}
