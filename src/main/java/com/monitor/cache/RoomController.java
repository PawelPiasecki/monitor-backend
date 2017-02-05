package com.monitor.cache;

import com.monitor.model.Room;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by artur on 2/2/17.
 */
@RestController
@RequestMapping("/")
public class RoomController {

    @RequestMapping("rooms")
    public ArrayList<Room> findAllRooms(){
        Room room1 = new Room();

        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(room1);

        return rooms;

    }
}
