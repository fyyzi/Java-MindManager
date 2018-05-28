package com.fyyzi.seckill.exceptions;

/**
 * 秒杀关闭异常
 *
 * @author 羽化荼 2018年5月26日
 */
public class SeckillClosedException extends SeckillAbsException {

    public SeckillClosedException(Integer code,String message) {
        super(code, message);
    }
}
