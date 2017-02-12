package com.monitor.controller;

import com.monitor.model.Room;
import com.monitor.repository.RoomRepository;
import com.monitor.repository.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by artur on 2/12/17.
 */
@Controller
public class DeleteRoomController {

    @Autowired
    RoomRepository roomRepository;
    @Autowired
    SystemRepository systemRepository;

    @RequestMapping("/view/manageRooms/{id}")
    public String roomsToDelete(Model model, @PathVariable long id) {

        com.monitor.model.System system = systemRepository.findOne(id);

        List<Room> roomList = (List<Room>) roomRepository.findBySystem(system);
        List<Room> roomToDelete = roomList.parallelStream().filter(s -> s.getSensors().isEmpty()).collect(Collectors.toList());
        model.addAttribute("rooms", roomToDelete);
        model.addAttribute("system", system);
        return "manageRooms";
    }

    @RequestMapping("/view/deleteRoom/{id}")
    public String roomsToDelete(@PathVariable int id) {
        Room room = roomRepository.findOne(id);
        com.monitor.model.System system = room.getSystem();
        roomRepository.delete(room);

        return "redirect:/view/manageRooms/{id}";
    }
}
