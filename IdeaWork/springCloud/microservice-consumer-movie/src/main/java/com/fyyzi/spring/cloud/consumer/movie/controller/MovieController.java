package com.fyyzi.spring.cloud.consumer.movie.controller;

import com.fyyzi.spring.cloud.consumer.movie.entitys.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/movie/{id}")
    public User findById(@PathVariable Long id) {
        return restTemplate.getForObject("http://MICROSERVICE-PROVIDER-USER/simple/" + id, User.class);
    }
}
