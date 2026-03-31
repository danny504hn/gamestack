package com.gamestack.inventario.service;

import com.gamestack.inventario.dto.PlataformaDTO;
import com.gamestack.inventario.model.Plataforma;
import com.gamestack.inventario.repository.PlataformaRepository;
import com.gamestack.inventario.repository.VideoJuegoRepository;
import com.gamestack.inventario.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlataformaService {

    private final PlataformaRepository repo;
    private final ModelMapper modelMapper;
    private final Utils utils;

    public PlataformaDTO createPlataforma(PlataformaDTO plataformaDTO) {
        Plataforma plataforma = modelMapper.map(plataformaDTO, Plataforma.class);
        repo.save(plataforma);
        return modelMapper.map(plataforma, PlataformaDTO.class);
    }
}
