package com.gamestack.inventario.kafka;

import com.gamestack.inventario.dto.VideoJuegoEventDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Producer {

    private final KafkaTemplate<String,VideoJuegoEventDTO> kafkaTemplate;

    public void publishEvent(VideoJuegoEventDTO eventDTO){
        String topicVideojuego = "videojuego-event";
        kafkaTemplate.send(topicVideojuego, String.valueOf(eventDTO.getId()),eventDTO);
    }

}
