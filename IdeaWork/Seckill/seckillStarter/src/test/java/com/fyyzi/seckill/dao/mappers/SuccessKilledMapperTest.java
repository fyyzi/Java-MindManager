package com.fyyzi.seckill.dao.mappers;

import com.fyyzi.seckill.SeckillApplicationTest;
import com.fyyzi.seckill.beans.SuccessKilled;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DuplicateKeyException;

import static org.junit.Assert.*;

public class SuccessKilledMapperTest extends SeckillApplicationTest {

    @Autowired
    private SuccessKilledMapper successKilledMapper;

    @Bean
    private SuccessKilled getTestSuccessKilled(){
        SuccessKilled successKilled = new SuccessKilled();
        successKilled.setSeckillId(1000l);
        successKilled.setUserPhone(1231l);
        return successKilled;
    }

    @Test
    public void insert() {
        SuccessKilled testSuccessKilled = getTestSuccessKilled();
        int insert = successKilledMapper.insert(testSuccessKilled);
        assertEquals(1, insert);
        System.out.println(insert);

        try {
            SuccessKilled testSuccessKilled1 = getTestSuccessKilled();
            insert = successKilledMapper.insert(testSuccessKilled1);
        } catch (DuplicateKeyException e) {
            insert = 0;
        }
        assertEquals(0, insert);
    }

    @Test
    public void queryByIdWithSeckill() {
        SuccessKilled testSuccessKilled = getTestSuccessKilled();
        testSuccessKilled.setState((byte)0);
        int insert = successKilledMapper.insert(testSuccessKilled);
        SuccessKilled successKilled = successKilledMapper.queryByIdWithSeckill(1000,123);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
        assertNotNull(successKilled);
        System.out.println(successKilled.getState());
    }
}