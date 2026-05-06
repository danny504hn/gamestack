package com.gamestack.inventario.service;

import com.gamestack.inventario.dto.VideoJuegoDTO;
import com.gamestack.inventario.exception.ResourceNotFoundException;
import com.gamestack.inventario.model.Plataforma;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    @Test
    void getByPlataforma_whenPlataformaExists_returnsFullListOfGames(){
        Plataforma p = new Plataforma("PC","juegos de pc");
        p.setId(1);
        LocalDateTime fechaFija = LocalDateTime.of(2026, 5, 6, 10, 0);
        List<VideoJuegoDTO> videoJuegoDTOList = List.of(
                new VideoJuegoDTO("JUEGO1", p.getId(), p.getNombre(), 23.3, 23,fechaFija),
                new VideoJuegoDTO("JUEGO2", p.getId(), p.getNombre(), 25, 23, fechaFija),
                new VideoJuegoDTO("JUEGO3", p.getId(), p.getNombre(), 25, 23, fechaFija)
        );
        List<VideoJuego> videoJuegoList = List.of(
                new VideoJuego("JUEGO1", p, 23.3, 23, fechaFija),
                new VideoJuego("JUEGO2", p, 25, 23, fechaFija),
                new VideoJuego("JUEGO3", p, 25, 23, fechaFija)
        );

        when(repo.findByPlataformaNombre(p.getNombre())).thenReturn(videoJuegoList);
        when(utils.mapList(videoJuegoList,VideoJuegoDTO.class)).thenReturn(videoJuegoDTOList);
        var result = service.getByPlataforma(p.getNombre());
        assertEquals(videoJuegoDTOList,result);
    }

    @Test
    void getByPlataforma_whenPlataformaNotExists_returnsEmptyList(){
        List<VideoJuegoDTO> l = new ArrayList<>();
        List<VideoJuego> lv = new ArrayList<>();
        when(repo.findByPlataformaNombre("ddd")).thenReturn(lv);
        when(utils.mapList(lv,VideoJuegoDTO.class)).thenReturn(l);
        var result = service.getByPlataforma("ddd");
        assertEquals(l,result);
    }
}
