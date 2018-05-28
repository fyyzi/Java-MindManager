package com.fyyzi.spring.cloud.consumer.movie;

import com.fyyzi.spring.config.TestConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;

@SpringBootApplication
@EnableEurekaClient

// 若采用配置文件配置Ribbon负载均衡，则优先级高于@RibbonClient 注解
@RibbonClient(name = "MICROSERVICE-PROVIDER-USER", configuration = TestConfiguration.class)
public class MicroServiceConsumerMovieApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(MicroServiceConsumerMovieApplication.class, args);
    }

}
