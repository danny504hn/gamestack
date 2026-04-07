package com.gamestack.inventario.repository;

import com.gamestack.inventario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository  extends JpaRepository<Usuario, Integer> {

    public Optional<Usuario> findByUsername(String username);
}
