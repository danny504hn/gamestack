package com.gamestack.inventario.security.services;

import com.gamestack.inventario.dto.LoginDTO;
import com.gamestack.inventario.dto.RegistroDTO;
import com.gamestack.inventario.dto.TokenResponseDTO;
import com.gamestack.inventario.dto.UsuarioDTO;
import com.gamestack.inventario.enums.Rol;
import com.gamestack.inventario.model.Usuario;
import com.gamestack.inventario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    PasswordEncoder passwordEncoder;
    UsuarioRepository repo;
    ModelMapper mapper;


    public UsuarioDTO registrar(RegistroDTO dto){
        Usuario target = new Usuario(dto.getUsername(),dto.getPassword());

        target.setPassword(passwordEncoder.encode(target.getPassword()));
        target.setRol(Rol.USER);
        return mapper.map(target, UsuarioDTO.class);

    }

    public TokenResponseDTO login(LoginDTO dto){

    }
}
