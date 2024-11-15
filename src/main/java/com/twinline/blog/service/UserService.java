package com.twinline.blog.service;

import com.twinline.blog.dto.UserDto;
import com.twinline.blog.entity.User;

public interface UserService {
    void registerUser(UserDto userDto);
}
