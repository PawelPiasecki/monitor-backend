package com.monitor.controller;

import com.monitor.model.Room;
import com.monitor.repository.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by artur on 2/11/17.
 */
@Controller
public class SystemViewController {
    @Autowired
    SystemRepository systemRepository;


    @ModelAttribute("systems")
    public List<com.monitor.model.System> rooms() {
        return (List<com.monitor.model.System>) systemRepository.findAll();
    }

    @RequestMapping("/view/systems")
    public String systemView(){
        return "systems";
    }
}
