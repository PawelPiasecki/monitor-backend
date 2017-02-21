package com.monitor.controller;

import com.monitor.model.User;
import com.monitor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.SecureRandom;

/**
 * Created by artur on 2/21/17.
 */
@Controller
public class ViewRegistrationController {



        @Autowired
        private UserRepository userRepository;



        @RequestMapping(value = "/view/registration", method = RequestMethod.POST)
        public String registration(User user) {

            String password  = user.getPasswordHash();

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(password);
            user.setPasswordHash(hashedPassword);
            userRepository.save(user);




            return "redirect:/view/systems";
        }

    @RequestMapping(value = "/view/registration")
    public String registrationGet(User user) {

        return "viewRegistration";
    }
}
