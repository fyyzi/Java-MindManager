package com.fyyzi.idealearning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Spring Boot 使用@Controller注解需要模板<br>
 * 单独使用@Controller 注解需要使用模板技术，增加@ResponseBody后返回书为Json（该注解主要是为了方式错误发生） 在实际使用中直接采用@RestController
 *
 * @author 息阳
 * 2017/12/27 14:19
 * @version 1.0
 * @deprecated 学习用
 */
@Controller
@ResponseBody
@Deprecated
public class IndexController {

    @RequestMapping(value = "/helloController", method = RequestMethod.GET)
    public String index() {
        return "index";
    }
}
