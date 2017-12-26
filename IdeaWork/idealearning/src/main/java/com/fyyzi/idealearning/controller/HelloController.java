package com.fyyzi.idealearning.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 息阳
 * 2017/12/26 14:51
 * @version 1.0
 */
@RestController
public class HelloController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String say(){
        return "Hello Spring Boot!";
    }
}
