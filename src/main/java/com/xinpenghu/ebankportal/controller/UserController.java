package com.xinpenghu.ebankportal.controller;

import com.xinpenghu.ebankportal.model.UserResponse;
import com.xinpenghu.ebankportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * Get user by Id or email
     * @param id
     * @param email
     * @return
     */
    @GetMapping
    public UserResponse getUserByIdOrEmail(@RequestParam(required = false) String id, @RequestParam(required = false) String email){
        if(id != null)
            return userService.getById(id);
        else
            return userService.getByEmail(email);
    }

}
