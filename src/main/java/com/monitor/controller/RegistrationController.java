package com.monitor.controller;


import com.monitor.model.User;
import com.monitor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by artur on 1/29/17.
 */
@Controller
public class RegistrationController {


    @Autowired
   private UserRepository userRepository;



    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(User user) {

        String password  = user.getPasswordHash();

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
       user.setPasswordHash(hashedPassword);
        userRepository.save(user);




        return "redirect:/view/systems";
    }

    @RequestMapping(value = "/registration")
    public String registrationGet(User user) {

        return "registration";
    }

    @RequestMapping("/test")
    public String start() {
        return "test";
    }
}
