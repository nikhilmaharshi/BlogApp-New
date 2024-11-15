package com.twinline.blog.controller;

import com.twinline.blog.dto.UserDto;
import com.twinline.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserDto userDto) {
        userService.registerUser(userDto);
        return "User registered successfully!";
    }


}
