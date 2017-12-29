package com.fyyzi.idealearning.jpa.repository;

import com.fyyzi.idealearning.jpa.domain.Girl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GirlRepositoryTest {

    @Autowired
    private GirlRepository girlRepository;

    private Girl girl = new Girl();

    @Test
    @Transactional
    public void selectTest() {
        // 查询全部
        girlRepository.findAll();

        // 根据Id查询
        girlRepository.findOne(1);

        // 添加
        girlRepository.save(girl);

        // 根据Id 删除
        girlRepository.delete(1);

        // 更新
        girlRepository.save(girl);

        // 删除
        girlRepository.delete(1);
        girlRepository.delete(girl);

    }
}