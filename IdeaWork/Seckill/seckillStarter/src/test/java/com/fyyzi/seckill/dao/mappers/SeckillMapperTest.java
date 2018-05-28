package com.fyyzi.seckill.dao.mappers;

import com.fyyzi.seckill.SeckillApplicationTest;
import com.fyyzi.seckill.beans.Seckill;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;


public class SeckillMapperTest extends SeckillApplicationTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillMapper seckillMapper;

    @Test
    public void queryAll() {
        List<Seckill> seckills = seckillMapper.queryAll(0, 10);
        for (Seckill seckill : seckills) {
            logger.info("seckill:{}" ,seckill);
        }
    }

    @Test
    public void queryByIdTerst() {
        Seckill seckill = seckillMapper.queryById(1000);
        System.out.println(seckill);
    }

    @Test
    public void reduceNumber() {
        Date killTime = new Date();
        int updateCount = seckillMapper.reduceNumber(1000, killTime);
        System.out.println(updateCount);
    }
}