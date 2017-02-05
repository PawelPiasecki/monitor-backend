package com.monitor.websockets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.SimpleMessageConverter;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 * Created by artur on 2/5/17.
 */
public class WebSocketController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("{sensorUrl}")
    @SendTo("/topic/showResult")
    public void addNumStomp(@DestinationVariable String sensorUrl ) throws Exception {

        simpMessagingTemplate.convertAndSend(sensorUrl, sensorUrl );
    }
}
