package com.monitor.controller;

import com.monitor.cache.SensorHistoryCache;
import com.monitor.model.User;
import com.monitor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

/**
 * Created by artur on 1/29/17.
 */
@Controller
public class RegistrationController {

    @Autowired
    private SecureRandom secureRandom;

    @Autowired
   private UserRepository userRepository;



    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(User user) {



        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        KeySpec spec = new PBEKeySpec(user.getLogin().toCharArray(), salt, 65536, 128);
        try {
            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = f.generateSecret(spec).getEncoded();
            Base64.Encoder enc = Base64.getEncoder();

            String passwordSalt = enc.encodeToString(salt);
            String passwordHash = enc.encodeToString(hash);

            System.out.printf("salt: %s%n", enc.encodeToString(salt));
            System.out.printf("hash: %s%n", enc.encodeToString(hash));


            User userToSave = new User();
            userToSave.setName(user.getName());
            userToSave.setLogin(user.getLogin());
            userToSave.setPasswordHash(passwordHash);
            userToSave.setPasswordSalt(user.getPasswordHash());

            System.out.println(userToSave.toString());
            userRepository.save(userToSave);
        } catch (Exception e) {
            System.out.println(e);
        }




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
