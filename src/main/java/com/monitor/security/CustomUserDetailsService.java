package com.monitor.security;


import com.monitor.model.User;
import com.monitor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by artur on 2/18/17.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = userRepository.findByLogin(username);


        if (username.isEmpty()) {
            throw new UsernameNotFoundException("No user present with username: " + username);
        } else {
            User user = users.get(0);
            List<String> userRoles = new ArrayList<String>();
            userRoles.add("USER");
            return new CustomUserDetails(user, userRoles);
        }
    }
}
