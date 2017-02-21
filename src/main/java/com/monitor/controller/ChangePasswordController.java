package com.monitor.controller;

import com.monitor.model.User;
import com.monitor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by artur on 2/21/17.
 */
@Controller
public class ChangePasswordController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/view/changePassword")
    public String changePassword(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        System.out.println(name);

        User user = userRepository.findByLogin(name).get(0);

        model.addAttribute("user", user);
        return "changePassword";
    }

    @RequestMapping(value = "view/changePassword", method = RequestMethod.POST)
    public String changePasswordPost(@ModelAttribute User user){
        String password  = user.getPasswordHash();



        System.out.println(user.getId());
        System.out.println(user.getLogin());
        System.out.println(user.getPasswordHash());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        user.setPasswordHash(hashedPassword);

        userRepository.save(user);

        return "redirect:/view/systems/";
    }
}
