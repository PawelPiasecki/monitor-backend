package com.monitor.websockets;


import com.monitor.cache.SensorHistoryCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * Created by artur on 2/5/17.
 */
@Controller
public class WebSocketController {
    @Autowired
    SensorHistoryCache sensorHistoryCache;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/broker/{systemId}/{sensorId}")
    @SendTo("/topic/showResult")
    public void addNum(@DestinationVariable String systemId, @DestinationVariable String sensorId) throws Exception {
        System.out.println(systemId);
        System.out.println(sensorId);

        String topic  =   systemId + "/" + sensorId;
        String message  = sensorHistoryCache.getHistoryCash().get(topic);
        System.out.println(message);
        simpMessagingTemplate.convertAndSend("/" + systemId + "/" + sensorId, message);
    }
}
