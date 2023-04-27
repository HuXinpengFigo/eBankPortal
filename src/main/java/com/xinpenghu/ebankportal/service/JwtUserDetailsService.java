package com.xinpenghu.ebankportal.service;


import java.util.ArrayList;

import com.xinpenghu.ebankportal.mongorepo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        String password = "12345678";
        if(userRepository.findUserByEmail(email).isPresent()) {
            String password = userRepository.findUserByEmail(email).get().getPassword();
            return new User(email, password,
                    new ArrayList<>());
        }else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }

}