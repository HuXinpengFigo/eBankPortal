package com.xinpenghu.ebankportal.service;

import com.xinpenghu.ebankportal.entity.User;
import com.xinpenghu.ebankportal.repository.UserRepository;
import com.xinpenghu.ebankportal.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserKafkaProducer userKafkaProducer;


    /**
     * Get user by Id
     * @param id
     * @return
     */
    public UserResponse getById(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            System.out.println("Empty User");
            return null;
        }
        System.out.println("Get User");
        return new UserResponse(optionalUser.get());
    }

    /**
     * Get user by email
     * @param email
     * @return
     */
    public UserResponse getByEmail(String email) {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        if (optionalUser.isEmpty()) {
            System.out.println("Empty User");
            return null;
        }
        System.out.println("Get User");
        return new UserResponse(optionalUser.get());
    }


}
