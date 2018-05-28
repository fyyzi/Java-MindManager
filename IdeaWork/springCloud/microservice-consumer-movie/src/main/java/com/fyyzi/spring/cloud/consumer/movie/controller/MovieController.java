package com.fyyzi.spring.cloud.consumer.movie.controller;

import com.fyyzi.spring.cloud.consumer.movie.entitys.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
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


    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @RequestMapping("/test")
    public String test() {

        ServiceInstance serviceInstance = this.loadBalancerClient.choose("MICROSERVICE-PROVIDER-USER");

        System.out.println(serviceInstance.getHost() + ":" + serviceInstance.getPort() + ":" + serviceInstance.getServiceId());

        ServiceInstance serviceInstance2 = this.loadBalancerClient.choose("MICROSERVICE-PROVIDER-USER2");
        System.err.println(serviceInstance2.getHost() + ":" + serviceInstance2.getPort() + ":" + serviceInstance2.getServiceId());


        return "1";
    }
}
