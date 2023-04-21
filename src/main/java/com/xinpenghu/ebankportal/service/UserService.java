package com.xinpenghu.ebankportal.service;

import com.xinpenghu.ebankportal.entity.User;
import com.xinpenghu.ebankportal.interfaces.UserRepository;
import com.xinpenghu.ebankportal.model.AddUserRequest;
import com.xinpenghu.ebankportal.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserResponse add(AddUserRequest request) {
        User user = userRepository.save(new User(request));
        return new UserResponse(user);
    }

    public UserResponse getById(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            System.out.println("Empty User");
            return null;
        }
        System.out.println("Get User");
        return new UserResponse(optionalUser.get());
    }

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
