package com.gamestack.inventario.repository;

import com.gamestack.inventario.model.VideoJuego;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoJuegoRepository  extends JpaRepository<VideoJuego, Integer> {
    public List<VideoJuego> findByPlataforma(String plataforma );
}
