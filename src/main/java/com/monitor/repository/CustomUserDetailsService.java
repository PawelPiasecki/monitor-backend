package com.monitor.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by artur on 2/12/17.
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        com.monitor.model.User user = userRepository.findByLogin(s).get(0);
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPasswordSalt(),
           getGrantedAuthorities(user));
    }

    private List<GrantedAuthority> getGrantedAuthorities(com.monitor.model.User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority("ROLE_"+"ADMIN"));
        System.out.print("authorities :"+authorities);
        return authorities;
    }
}
