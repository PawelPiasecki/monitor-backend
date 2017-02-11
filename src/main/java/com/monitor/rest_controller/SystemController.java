package com.monitor.rest_controller;

import com.monitor.model.Room;
import com.monitor.model.Sensor;
import com.monitor.repository.RoomRepository;
import com.monitor.repository.SensorRepository;
import com.monitor.repository.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by artur on 2/5/17.
 */
@RestController
public class SystemController {

    @Autowired
    SystemRepository systemRepository;



    @RequestMapping("/systems")
    public List<com.monitor.model.System> systems(){
        return (List<com.monitor.model.System>) systemRepository.findAll();
    }


}
