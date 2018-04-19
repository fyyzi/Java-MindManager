package com.fyyzi.spring.cloud.provider.user.controller;

import com.fyyzi.spring.cloud.provider.user.dao.UserRepository;
import com.fyyzi.spring.cloud.provider.user.entitys.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/simple/{id}")
    public User findById(@PathVariable Long id) {
        User user = userRepository.findOne(id);
        return user;
    }

}
