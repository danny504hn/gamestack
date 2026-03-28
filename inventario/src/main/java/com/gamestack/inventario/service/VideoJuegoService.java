package com.gamestack.inventario.service;

import com.gamestack.inventario.utils.Utils;
import com.gamestack.inventario.dto.VideoJuegoDTO;
import com.gamestack.inventario.exception.ResourceNotFoundException;
import com.gamestack.inventario.model.VideoJuego;
import com.gamestack.inventario.repository.VideoJuegoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoJuegoService {

    private final VideoJuegoRepository repo;
    private final ModelMapper modelMapper;
    private final Utils utils;


    public List<VideoJuegoDTO> getByPlataforma(String plataforma){
        List<VideoJuego> juegos = repo.findByPlataforma(plataforma);

        return utils.mapList(juegos,VideoJuegoDTO.class) ;
    }

    public VideoJuegoDTO getById(int id){
       VideoJuego target = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("NO SE ENCONTRO EL JUEGO"));
       return modelMapper.map(target, VideoJuegoDTO.class);
    }

    public List<VideoJuegoDTO> getAll(){
        List<VideoJuego> juegos = repo.findAll();
        return utils.mapList(juegos, VideoJuegoDTO.class);
    }

    public VideoJuegoDTO createVideojuego(VideoJuegoDTO videoJuego){
        if(videoJuego.getStock() < 0){
            videoJuego.setStock(0);
        }
        repo.save(modelMapper.map(videoJuego,VideoJuego.class));
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
}
