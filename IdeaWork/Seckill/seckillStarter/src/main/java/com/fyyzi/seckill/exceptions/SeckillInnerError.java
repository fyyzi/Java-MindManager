package com.fyyzi.seckill.exceptions;

/**
 * 秒杀内部运行出现异常
 */
public class SeckillInnerError extends SeckillAbsException {
    public SeckillInnerError(Integer code, String message) {
        super(code, message);
    }
}
