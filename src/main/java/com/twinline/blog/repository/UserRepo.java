package com.twinline.blog.repository;

import com.twinline.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepo extends JpaRepository<User,Integer> {
    UserDetails findUserByUsername(String username);
}
