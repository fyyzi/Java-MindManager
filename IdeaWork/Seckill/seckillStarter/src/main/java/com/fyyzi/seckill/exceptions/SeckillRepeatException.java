package com.fyyzi.seckill.exceptions;

/**
 * 重复秒杀异常
 * @author 羽化荼 2018年5月26日
 */
public class SeckillRepeatException extends SeckillAbsException {

    public SeckillRepeatException(Integer code,String message) {
        super(code, message);
    }
}
