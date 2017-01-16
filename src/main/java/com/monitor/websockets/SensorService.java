package com.monitor.websockets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class SensorService {

    private AtomicInteger counter = new AtomicInteger(0);

    @Autowired
    SensorHandler sensorHandler;

    @Scheduled(fixedDelay = 1000)
    public void sendCounterUpdate() {
        sensorHandler.counterIncrementedCallback(counter.incrementAndGet());
    }

}