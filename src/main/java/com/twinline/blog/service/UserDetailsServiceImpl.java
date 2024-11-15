package com.twinline.blog.service;

import com.twinline.blog.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userRepo.findUserByUsername(username);

        if (userDetails == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        // Create and return the Spring Security UserDetails object
        return org.springframework.security.core.userdetails.User.builder()
                .username(userDetails.getUsername())
                .password(userDetails.getPassword())
                .roles("USER")
                .build();
    }
}
