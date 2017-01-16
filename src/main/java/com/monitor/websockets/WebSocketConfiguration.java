package com.monitor.websockets;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * Created by artur on 1/14/17.
 */
@EnableWebSocket
@Configuration
@EnableScheduling
@RefreshScope
public class WebSocketConfiguration implements WebSocketConfigurer {



    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        registry.addHandler(sensorHandler(), "/sensor")
                .addInterceptors(new HttpSessionHandshakeInterceptor()).setAllowedOrigins("*");

    }



    @Bean
    public WebSocketHandler sensorHandler() {
        return new SensorHandler();
    }




}
