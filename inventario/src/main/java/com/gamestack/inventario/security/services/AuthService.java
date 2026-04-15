package com.gamestack.inventario.security.services;

import com.gamestack.inventario.dto.LoginDTO;
import com.gamestack.inventario.dto.RegistroDTO;
import com.gamestack.inventario.dto.TokenResponseDTO;
import com.gamestack.inventario.dto.UsuarioDTO;
import com.gamestack.inventario.enums.Rol;
import com.gamestack.inventario.model.Usuario;
import com.gamestack.inventario.repository.UsuarioRepository;
import com.gamestack.inventario.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository repo;
    private final ModelMapper mapper;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager manager;

    public UsuarioDTO registrar(RegistroDTO dto){
        Usuario target = new Usuario(dto.getUsername(),dto.getPassword());

        target.setPassword(passwordEncoder.encode(target.getPassword()));
        target.setRol(Rol.USER);
        Usuario newUser = repo.save(target);
        return mapper.map(newUser, UsuarioDTO.class);
    }

    //Explicacion propia para futuros logins>>> El metodo recibe un loginDTO (username y passwod)
    //Para conseguir el token creamos una variable Authentication, la cual llama al manager que nos llama ala metodo authenticate
    // Importante pasarle un UsernamePasswordAtuthenticationToken y a este pasarle el dto.getUsername() y el dt.getPassword()
    //Luego generamos el token con el jwtUtils, que tengo, y devolver este token.
    public TokenResponseDTO login(LoginDTO dto){
        Authentication auth = manager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );
        String token = jwtUtils.generarToken(auth);
        return new TokenResponseDTO(token);
    }
}
