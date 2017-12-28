package com.fyyzi.idealearning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author 息阳
 * 2017/12/26 14:51
 * @version 1.0
 */
//@RestController
@RequestMapping(value = {"hello", "hi"})
public class HelloController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String say() {
        return "Hello Spring Boot!";
    }

    @RequestMapping(value = "/value/{id}", method = RequestMethod.GET)
    public String getId(@PathVariable(value = "id") String myId) {
        return myId;
    }

    @RequestMapping(value = "/value", method = RequestMethod.GET)
    public String getId2(@RequestParam(value = "id", required = false, defaultValue = "0") String myId) {
        return myId;
    }

    @GetMapping(value = "/value/id")
    public String getId3(@RequestParam(value = "id") String myId) {
        return myId;
    }

}
