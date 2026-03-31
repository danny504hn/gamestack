package com.gamestack.inventario.repository;

import com.gamestack.inventario.model.Plataforma;
import com.gamestack.inventario.model.VideoJuego;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoJuegoRepository  extends JpaRepository<VideoJuego, Integer> {
    public List<VideoJuego> findByPlataformaNombre(String plataforma );
}
