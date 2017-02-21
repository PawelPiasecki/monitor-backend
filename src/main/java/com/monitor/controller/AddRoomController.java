package com.monitor.controller;

import com.monitor.model.Room;
import com.monitor.repository.RoomRepository;
import com.monitor.repository.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

/**
 * Created by artur on 2/9/17.
 */
@Controller
public class AddRoomController {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    SystemRepository systemRepository;

    @RequestMapping("view/addRoom/{id}")
    public String registrationGet(Model model, @PathVariable long id) {

        Room room = new Room();
        com.monitor.model.System system = systemRepository.findOne(id);
        room.setSystem(system);
        model.addAttribute("rooms", roomRepository.findBySystem(system));
        model.addAttribute(room);
        return "addRoom";
    }

    @RequestMapping(value = "view/addRoom/{systemId}", method = RequestMethod.POST)
    public String registration(Room room, @PathVariable long systemId) {

        Room roomToSave = new Room();
        roomToSave.setName(room.getName());
        com.monitor.model.System system = systemRepository.findOne(systemId);
        roomToSave.setSystem(system);
        system.addRoom(roomToSave);
        systemRepository.save(system);
        roomRepository.save(roomToSave);
        System.out.println(systemId);
        String url = "redirect:/view/addRoom/" + systemId;

        return url;
    }

    @RequestMapping(value = "/view/loginxd", method = RequestMethod.GET)
    public String getLoginPage() {
        return "login1";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String redirectxd() {
        return "redirect:/view/systems";
    }



}
