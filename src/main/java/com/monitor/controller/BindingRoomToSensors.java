package com.monitor.controller;

import com.monitor.model.Room;
import com.monitor.model.Sensor;
import com.monitor.model.User;
import com.monitor.repository.RoomRepository;
import com.monitor.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by artur on 2/9/17.
 */
@Controller
public class BindingRoomToSensors {

    @Autowired
    SensorRepository sensorRepository;

    @Autowired
    RoomRepository roomRepository;

    @RequestMapping("/bindSensorToRoom")
    public String index(Model model) {
        SensorFormWrapper sensorFormWrapper = new SensorFormWrapper();
        List<Sensor> sensorList = (List<Sensor>) sensorRepository.findAll();
        sensorFormWrapper.setSensors(sensorList);
        System.out.println(sensorFormWrapper.getSensors());
        model.addAttribute("wrapper", sensorFormWrapper);

        return "bindSensorToRoom";

    }


    @RequestMapping(value = "/bindSensorToRoom", method = RequestMethod.PUT)
    public String processQuery(@ModelAttribute SensorFormWrapper wrapper, Model model) {


        System.out.println(wrapper.getSensors().get(1).getId());
        System.out.println(wrapper.getSensors().get(1).getRoom().getName());

        sensorRepository.save(wrapper.getSensors());




        List<Sensor> sensorList = (List<Sensor>) sensorRepository.findAll();
        wrapper.setSensors(sensorList);
        model.addAttribute("wrapper", wrapper);

        return "redirect:bindSensorToRoom.html";
    }

    @ModelAttribute("rooms")
    public List<Room> rooms() {
        return (List<Room>) roomRepository.findAll();
    }

}
