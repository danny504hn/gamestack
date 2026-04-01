package com.gamestack.inventario.service;

import com.gamestack.inventario.exception.StockInsuficienteException;
import com.gamestack.inventario.model.MovimientoStock;
import com.gamestack.inventario.model.Plataforma;
import com.gamestack.inventario.repository.PlataformaRepository;
import com.gamestack.inventario.utils.Utils;
import com.gamestack.inventario.dto.VideoJuegoDTO;
import com.gamestack.inventario.exception.ResourceNotFoundException;
import com.gamestack.inventario.model.VideoJuego;
import com.gamestack.inventario.repository.VideoJuegoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoJuegoService {

    private final VideoJuegoRepository repo;
    private final ModelMapper modelMapper;
    private final Utils utils;
    private final PlataformaRepository repoPlataforma;


    public List<VideoJuegoDTO> getByPlataforma(String plataforma){
        List<VideoJuego> juegos = repo.findByPlataformaNombre(plataforma);

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
        Plataforma p = repoPlataforma.findById(videoJuego.getPlataformaId()).orElseThrow(() -> new ResourceNotFoundException("No se encontro la categoria"));
        VideoJuego v = modelMapper.map(videoJuego,VideoJuego.class);
        v.setPlataforma(p);
        repo.save(v);
        return modelMapper.map(videoJuego,VideoJuegoDTO.class);
    }

    public boolean deleteById(int id){
        boolean deleted = false;
        if(repo.existsById(id)){
            repo.deleteById(id);
            deleted = true;
        }
        return deleted;
    }

    @Transactional
    public VideoJuegoDTO modificarStock(Integer idJuego, int cantidad, String motivo){

        VideoJuego target = repo.findById(idJuego)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No se econtro el juego ")
                );

        int stockActual = target.getStock();
        int newStock = stockActual + cantidad;
        if(newStock < 0) throw new StockInsuficienteException("Stock insuficiente");
        target.setStock(newStock);
        MovimientoStock mS = new MovimientoStock(cantidad, LocalDateTime.now(), motivo);
        target.addMovimiento(mS);
        repo.save(target);

        return modelMapper.map(target,VideoJuegoDTO.class);
    }

}
