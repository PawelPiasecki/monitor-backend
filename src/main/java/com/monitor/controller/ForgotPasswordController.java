package com.monitor.controller;

import com.monitor.model.User;
import com.monitor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by artur on 2/21/17.
 */
@Controller
public class ForgotPasswordController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/forgotPassword")
    public String forgotPasswordGet(User user){
        return "forgotPassword";
    }

    @RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
    public String forgotPassword(@ModelAttribute User user){

        User newUser  = userRepository.findByLogin(user.getLogin()).get(0);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPasswordHash());
        newUser.setPasswordHash(hashedPassword);


        System.out.println(newUser.getId());
        userRepository.save(newUser);
        return "redirect:/forgotPassword";
    }
}
