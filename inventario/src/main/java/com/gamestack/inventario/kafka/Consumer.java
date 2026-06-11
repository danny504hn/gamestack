package com.gamestack.inventario.kafka;

import com.gamestack.inventario.dto.VideoJuegoEventDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Consumer {

    private static final Logger LOG = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = {"videojuego-event"},groupId = "inventario-group")
    public void consumeEvent(VideoJuegoEventDTO eventDTO){
        LOG.info("EL EVENTO LLEGO ");
    }
}
