package com.gamestack.inventario.service;

import com.gamestack.inventario.dto.VideoJuegoDTO;
import com.gamestack.inventario.exception.ResourceNotFoundException;
import com.gamestack.inventario.model.VideoJuego;
import com.gamestack.inventario.repository.PlataformaRepository;
import com.gamestack.inventario.repository.VideoJuegoRepository;
import com.gamestack.inventario.utils.Utils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class) //SIRVE PARA ACTIVAR MOCKITO
public class VideoJuegoServiceTest {

    @Mock // simula el comportamiento real.
    VideoJuegoRepository repo;

    @Mock
    ModelMapper modelMapper;

    @Mock

    Utils utils;
    @Mock
    PlataformaRepository repoPlataforma;

    @InjectMocks // injeyacta todos los mocks al service real.
    VideoJuegoService service;


    @Test // crea un test
    void getById_whenGameExists_returnGame(){
        //Arrange
        VideoJuego target = new VideoJuego();
        target.setId(1);
        target.setTitulo("Age of Empires");

        VideoJuegoDTO dtoTarget = new VideoJuegoDTO();
        dtoTarget.setTitulo("Age of Empires");

        when(repo.findById(1)).thenReturn(Optional.of(target));
        when(modelMapper.map(target,VideoJuegoDTO.class)).thenReturn(dtoTarget);

        //Act
        VideoJuegoDTO result = service.getById(1);

        //Assert

        assertEquals("Age of Empires",result.getTitulo());
    }
    @Test
    void getById_WhenGameNotExists_throwsException(){
        //arrange
        when(repo.findById(99999)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.getById(99999));
    }
}
