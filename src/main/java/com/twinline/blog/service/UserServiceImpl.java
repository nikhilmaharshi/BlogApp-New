package com.twinline.blog.service;

import com.twinline.blog.dto.UserDto;
import com.twinline.blog.entity.User;
import com.twinline.blog.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public void registerUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepo.save(user);
    }
}
