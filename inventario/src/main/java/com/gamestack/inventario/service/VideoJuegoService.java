package com.gamestack.inventario.service;

import com.gamestack.inventario.dto.VideoJuegoDTO;
import com.gamestack.inventario.exception.ResourceNotFoundException;
import com.gamestack.inventario.model.VideoJuego;
import com.gamestack.inventario.repository.VideoJuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoJuegoService {

    @Autowired
    VideoJuegoRepository repo;

    public List<VideoJuegoDTO> getByPlataforma(String plataforma){
        List<VideoJuego> juegos = repo.findByPlataforma(plataforma);
        List<VideoJuegoDTO> juegosDTO = new ArrayList<>();

        for(VideoJuego v : juegos){
            juegosDTO.add(videoJuegoToVideojuegoDTO(v));
        }
        return juegosDTO;
    }

    public VideoJuegoDTO getById(int id){
       VideoJuego target = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("NO SE ENCONTRO EL JUEGO"));
       return videoJuegoToVideojuegoDTO(target);
    }

    public List<VideoJuegoDTO> getAll(){
        List<VideoJuego> juegos = repo.findAll();
        List<VideoJuegoDTO> juegosDTO = new ArrayList<>();

        for(VideoJuego v : juegos){
            juegosDTO.add(videoJuegoToVideojuegoDTO(v));
        }
        return juegosDTO;
    }

    public VideoJuegoDTO createVideojuego(VideoJuegoDTO videoJuego){
        if(videoJuego.getStock() < 0){
            videoJuego.setStock(0);
        }
       VideoJuego juegoEntity = videoJuegoDTOToVideoJuego(videoJuego);
        repo.save(juegoEntity);
        return videoJuego;
    }

    public boolean deleteById(int id){
        boolean deleted = false;
        if(repo.existsById(id)){
            repo.deleteById(id);
            deleted = true;
        }
        return deleted;
    }

    public VideoJuegoDTO videoJuegoToVideojuegoDTO(VideoJuego game){
        return new VideoJuegoDTO(game.getId(), game.getTitulo(), game.getPlataforma(), game.getPrecio(),game.getStock(), game.getFechaLanzamiento());
    }

    public VideoJuego videoJuegoDTOToVideoJuego(VideoJuegoDTO game){
        return  new VideoJuego(game.getId(), game.getTitulo(), game.getPlataforma(), game.getPrecio(),game.getStock(), game.getFechaLanzamiento());
    }


}
