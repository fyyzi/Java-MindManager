package com.fyyzi.idealearning.jpa.services.impl;

import com.fyyzi.idealearning.jpa.domain.Girl;
import com.fyyzi.idealearning.jpa.repository.GirlRepository;
import com.fyyzi.idealearning.jpa.services.GirlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author 息阳
 * 2017/12/29 15:15
 * @version 1.0
 */
@Service
public class GirlServiceImpl implements GirlService {

    @Autowired
    private GirlRepository girlRepository;

    @Override
    public Girl getGirlById(Integer id) {
        return girlRepository.findOne(id);
    }

    @Override
    public List<Girl> getAllGirl() {
        return girlRepository.findAll();
    }

    @Override
    public List<Girl> getGirlByAge(Integer age) {
        return girlRepository.findByAge(age);
    }

    @Override
    public Girl addGirl(Girl girl) {
        return girlRepository.save(girl);
    }


}
