package com.fyyzi.seckill.dao.redis;

import com.fyyzi.seckill.SeckillApplicationTest;
import com.fyyzi.seckill.beans.Seckill;
import com.fyyzi.seckill.dao.mappers.SeckillMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class seckillredisdaoTest extends SeckillApplicationTest {

    @Autowired
    private SeckillRedisDao seckillRedisDao;

    @Autowired
    private SeckillMapper seckillMapper;

    @Test
    public void getSeckill() {

        Seckill seckill = seckillRedisDao.getSeckill(1000);
        System.out.println(seckill);
    }

    @Test
    public void putSeckill() {

        Seckill seckill = seckillMapper.queryById(1000);

        seckillRedisDao.putSeckill(seckill);

        getSeckill();
    }
}