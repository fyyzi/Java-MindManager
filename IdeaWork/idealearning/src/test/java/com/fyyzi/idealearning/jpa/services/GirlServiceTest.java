package com.fyyzi.idealearning.jpa.services;

import com.fyyzi.idealearning.IdealearningApplicationTests;
import com.fyyzi.idealearning.jpa.domain.Girl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class GirlServiceTest extends IdealearningApplicationTests {

    @Autowired
    private GirlService girlService;

    @Test
    public void getGirlById() {
        Girl girl = girlService.getGirlById(1);
        System.out.println(girl);
    }

    @Test
    public void getAllGirl() {
        List<Girl> girlList = girlService.getAllGirl();
        for (Girl girl : girlList) {
            System.out.println(girl);
        }
    }
}