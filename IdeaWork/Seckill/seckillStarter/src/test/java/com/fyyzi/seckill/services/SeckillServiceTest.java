package com.fyyzi.seckill.services;

import com.fyyzi.seckill.SeckillApplicationTest;
import com.fyyzi.seckill.beans.Seckill;
import com.fyyzi.seckill.dto.ExportUrl;
import com.fyyzi.seckill.dto.SeckillExecution;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class SeckillServiceTest extends SeckillApplicationTest {

    private  Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() {
        List<Seckill> seckillList = seckillService.getSeckillList();
        for (Seckill seckill : seckillList) {
            logger.info("getSeckillLIst:{}",seckill);
        }
    }

    @Test
    public void getSeckillByPrimaryKey() {
        Seckill seckillByPrimaryKey = seckillService.getSeckillByPrimaryKey(1000l);
        System.out.println(seckillByPrimaryKey);
    }

    @Test
    public void exportSeckillUrl() {
        ExportUrl exportUrl = seckillService.exportSeckillUrl(1000L);
        logger.info("exportSeckillUrl:{}",exportUrl);
        logger.info("md5{}",exportUrl.getMd5());
    }

    @Test
    public void executeSeckill() {
        long seckillId = 1000;
        long userPhone = 12311L;
        ExportUrl exportUrl = seckillService.exportSeckillUrl(1000l);
        String md5 = exportUrl.getMd5();
        logger.info("md5{}",md5);
        if (exportUrl.isExported()){
            SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);
            logger.info("executeSeckill:{}",seckillExecution);
        }else {
            System.out.println(1111);
        }

    }
}