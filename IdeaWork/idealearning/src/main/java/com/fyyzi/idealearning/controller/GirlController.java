package com.fyyzi.idealearning.controller;

import com.fyyzi.idealearning.propertiess.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 息阳
 * 2017/12/27 13:06
 * @version 1.0
 */
@RestController
public class GirlController {

    @Autowired
    private GirlProperties girlProperties;

    @Value("${girl.age}")
    private Integer age;

    @RequestMapping(value = "/girl", method = RequestMethod.GET)
    public String getGirl() {
        return girlProperties.getContent();
    }
}
