package com.gamestack.inventario.service;

import com.gamestack.inventario.model.VideoJuego;
import com.gamestack.inventario.repository.VideoJuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class VideoJuegoService {

    @Autowired
    VideoJuegoRepository repo;

    public List<VideoJuego> GetByPlataforma(String plataforma){
        return  repo.findByPlataforma(plataforma);
    }

    public Optional<VideoJuego> GetById(int id){
        return repo.findById(id);
    }

    public List<VideoJuego> GetAll(){
        return repo.findAll();
    }

    public VideoJuego CreateVideojuego(VideoJuego videoJuego){
        if(videoJuego.getStock() < 0){
            videoJuego.setStock(0);
        }
        repo.save(videoJuego);
        return videoJuego;
    }

    public boolean DeleteById(int id){
        boolean deleted = false;
        if(repo.existsById(id)){
            repo.deleteById(id);
            deleted = true;
        }
        return deleted;
    }


}
