package com.fyyzi.idealearning.jpa.controller;

import com.fyyzi.idealearning.jpa.domain.Girl;
import com.fyyzi.idealearning.jpa.domain.ResultModel;
import com.fyyzi.idealearning.jpa.services.GirlService;

import com.fyyzi.idealearning.jpa.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * @author 息阳
 * 2017/12/29 15:13
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/girl")
public class GirlController {

    private final static Logger logger = LoggerFactory.getLogger(GirlController.class);

    @Autowired
    private GirlService girlService;

    @PostMapping(value = "/girls")
    public ResultModel<Girl> addGirl(@Valid Girl girl, BindingResult bindingResult) {
        ResultModel resultModel;
        // 如果有错误消息，则打印错误消息并且返回空值
        if (bindingResult.hasErrors()) {
            String message = bindingResult.getFieldError().getDefaultMessage();
            resultModel = ResultUtils.error(1, message);
        }

        resultModel = ResultUtils.success(girlService.addGirl(girl));

        return resultModel;
    }

    @GetMapping(value = "/girls")
    public List<Girl> getAllGirl() {
        return girlService.getAllGirl();
    }

    @GetMapping(value = "/getAge/{id}")
    public Girl getAge(@PathVariable("id") Integer id){
        return girlService.getAge(id);
    }
}
