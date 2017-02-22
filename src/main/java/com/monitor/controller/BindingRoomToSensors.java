package com.monitor.controller;

import com.monitor.model.Room;
import com.monitor.model.Sensor;
import com.monitor.model.User;
import com.monitor.repository.RoomRepository;
import com.monitor.repository.SensorRepository;
import com.monitor.repository.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    SystemRepository systemRepository;

    @RequestMapping("view/bindSensorToRoom/{id}")
    public String index(Model model, @PathVariable long id) {
        SensorFormWrapper sensorFormWrapper = new SensorFormWrapper();
        com.monitor.model.System system = systemRepository.findOne(id);
        List<Sensor> sensorList = (List<Sensor>) sensorRepository.findByIsActiveAndSystem(true, system);
        sensorList.forEach(s -> System.out.println(s.getIsActive() + " " + s.getName()));
        sensorFormWrapper.setSensors(sensorList);
        System.out.println(sensorFormWrapper.getSensors());
        model.addAttribute("wrapper", sensorFormWrapper);
        model.addAttribute("rooms", roomRepository.findBySystem(system));
        model.addAttribute("system", system);
        return "bindSensorToRoom";

    }


    @RequestMapping(value = "view/bindSensorToRoom/{id}", method = RequestMethod.PUT)
    public String processQuery(@ModelAttribute SensorFormWrapper wrapper, Model model, @PathVariable long id) {


        System.out.println(wrapper.getSensors().get(1).getId());
        System.out.println(wrapper.getSensors().get(1).getRoom().getName());

        com.monitor.model.System system = systemRepository.findOne(id);
        wrapper.getSensors().forEach(s -> s.setActive(true));
        sensorRepository.save(wrapper.getSensors());


        List<Sensor> sensorList = (List<Sensor>) sensorRepository.findByIsActiveAndSystem(true, system);

        wrapper.setSensors(sensorList);
        model.addAttribute("wrapper", wrapper);

        return "redirect:/view/bindSensorToRoom/"+id;
    }


}
