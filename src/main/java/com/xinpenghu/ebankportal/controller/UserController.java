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

    /*
     * @method :- getUserByIdOrEmail
     * @Description :- Get Current User Based on Id or Email
     * @params:- Id, email
     * */
    @GetMapping
    public UserResponse getUserByIdOrEmail(@RequestParam(required = false) String id, @RequestParam(required = false) String email){
        if(id != null)
            return userService.getById(id);
        else
            return userService.getByEmail(email);
    }

}
