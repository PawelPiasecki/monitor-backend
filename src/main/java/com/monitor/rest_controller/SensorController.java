package com.monitor.rest_controller;

import com.monitor.model.Sensor;
import com.monitor.repository.SensorRepository;
import com.monitor.repository.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by artur on 10.07.17.
 */
@RestController
public class SensorController {

    @Autowired
    SystemRepository systemRepository;

    @Autowired
    SensorRepository sensorRepository;

    @RequestMapping("/api/sensors/{systemId}")
    public List<Sensor> sensors(@PathVariable long systemId) {
        com.monitor.model.System system = systemRepository.findOne(systemId);
        List<Sensor> sensors = sensorRepository.findByIsActiveAndSystem(true, system);
        return sensors;
    }

    @RequestMapping("/api/sensors")
    public List<Sensor> getAllSensors() {
        List<Sensor> sensors = (ArrayList) sensorRepository.findAll();
        return sensors;
    }
}
