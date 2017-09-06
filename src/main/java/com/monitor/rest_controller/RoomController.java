package com.monitor.rest_controller;

import com.monitor.model.Room;
import com.monitor.repository.RoomRepository;
import com.monitor.repository.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by artur on 10.07.17.
 */
@RestController
public class RoomController {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    SystemRepository systemRepository;

    @RequestMapping("/api/rooms/{id}")
    public List<Room> roomList(@PathVariable int id){

        com.monitor.model.System  system = systemRepository.findOne(Integer.toUnsignedLong(id));
        List<Room> rooms = roomRepository.findBySystem(system);

        return rooms;

    }
}
