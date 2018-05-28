package com.fyyzi.seckill.services;

import com.fyyzi.seckill.beans.Seckill;
import com.fyyzi.seckill.dto.ExportUrl;
import com.fyyzi.seckill.dto.SeckillExecution;
import com.fyyzi.seckill.exceptions.SeckillAbsException;
import com.fyyzi.seckill.exceptions.SeckillClosedException;
import com.fyyzi.seckill.exceptions.SeckillRepeatException;

import java.util.List;

/**
 * 业务接口：
 *
 * @author 羽化荼 2018年5月26日
 */
public interface SeckillService {

    /**
     * 查询所有秒杀记录
     *
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 根据主键查询秒杀记录
     *
     * @param seckillId
     * @return
     */
    Seckill getSeckillByPrimaryKey(Long seckillId);

    /**
     * 商品秒杀开启时输出秒杀接口的地址，否则输出系统时间和秒杀时间
     *
     * @param seckillId
     */
    ExportUrl exportSeckillUrl(Long seckillId);

    /**
     * 执行秒杀操作
     * @param SeckillId
     * @param userPhone
     * @param md5
     * @return
     * @throws SeckillAbsException
     * @throws SeckillRepeatException 重复秒杀
     * @throws SeckillClosedException 秒杀关闭
     */
    SeckillExecution executeSeckill(long SeckillId, long userPhone, String md5) throws SeckillAbsException, SeckillRepeatException, SeckillClosedException;


}
