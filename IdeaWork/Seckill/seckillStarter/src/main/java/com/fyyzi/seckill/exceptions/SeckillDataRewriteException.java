package com.fyyzi.seckill.exceptions;

/**
 * MD5被改写异常
 */
public class SeckillDataRewriteException extends SeckillAbsException {

    public SeckillDataRewriteException(Integer code, String message) {
        super(code,message);
    }
}
