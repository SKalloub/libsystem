package com.example.libsystem.services.securityServices;

import com.example.libsystem.exceptions.WrongUserNameOrPasswordException;
import com.example.libsystem.handlers.AuthorHandler;
import com.example.libsystem.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private static final Logger logger = LogManager.getLogger(AuthorHandler.class);

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException
    {
        com.example.libsystem.model.User user = userRepository
                .findByUsername(username)
                .orElseThrow((WrongUserNameOrPasswordException::new));
        User us = new User(user.getUsername(),
                user.getPassword(), new ArrayList<>());
        return us;
    }
}
