package com.xinpenghu.ebankportal.controller;

import com.xinpenghu.ebankportal.model.AddUserRequest;
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
     * @method :- addUser
     * @Description :- Adding New User To database;
     * @params:- AddUserRequest
     * */
    @PostMapping
    public String addUser(@RequestBody AddUserRequest request){
        return userService.add(request);
    }

    /*
     * @method :- getUserById
     * @Description :- Get Current User Based on Id or Email
     * @params:- Id
     * */
    @GetMapping
    public UserResponse getUserById(@RequestParam(required = false) String id, @RequestParam(required = false) String email){
        if(id != null)
            return userService.getById(id);
        else
            return userService.getByEmail(email);
    }

}
