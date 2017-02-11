package com.monitor.controller;

import com.monitor.model.Room;
import com.monitor.repository.RoomRepository;
import com.monitor.repository.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by artur on 2/9/17.
 */
@Controller
public class AddRoomController {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    SystemRepository systemRepository;

    @RequestMapping("/addRoom")
    public String registrationGet(Room room){

        return "addRoom";
    }

    @RequestMapping(value = "/addRoom", method = RequestMethod.POST)
    public String registration(Room room){

        Room roomToSave = new Room();
        roomToSave.setName(room.getName());
        com.monitor.model.System system = systemRepository.findByName("home1").get(0);
        roomToSave.setSystem(system);
        system.addRoom(roomToSave);
        systemRepository.save(system);
        roomRepository.save(roomToSave);


        return "redirect:addRoom.html";


    }

    @ModelAttribute("rooms")
    public List<Room> rooms() {
        return (List<Room>) roomRepository.findAll();
    }
}
