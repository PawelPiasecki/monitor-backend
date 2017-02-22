package com.monitor.controller;

import com.monitor.model.Room;
import com.monitor.repository.RoomRepository;
import com.monitor.repository.SensorRepository;
import com.monitor.repository.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by artur on 2/21/17.
 */
@Controller
public class RemoveSystemController {

    @Autowired
    SystemRepository systemRepository;

    @Autowired
    RoomRepository roomRepository;

    @RequestMapping("/view/removeSystem/{id}")
    public String removeSystem(@PathVariable Long id) {

        com.monitor.model.System system = systemRepository.findOne(id);
        List<Room> roomList  = system.getRooms();
        roomList.forEach(s -> s.setSystem(null));
        system.setRooms(null);
        roomRepository.save(roomList);
        systemRepository.save(system);
        
        systemRepository.delete(id);

        return "redirect:/view/systems";
    }
}
