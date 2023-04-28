package com.xinpenghu.ebankportal.service;


import java.util.ArrayList;

import com.xinpenghu.ebankportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    /**
     * Check if user information correct
     * @param email
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if(userRepository.findUserByEmail(email).isPresent()) {
            String password = userRepository.findUserByEmail(email).get().getPassword();
            return new User(email, password,
                    new ArrayList<>());
        }else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }

}