package com.fyyzi.seckill.exceptions;

public abstract class SeckillAbsException extends RuntimeException {

    private Integer code;

    public SeckillAbsException(Integer code,String message) {
        super(message);
        this.code = code;
    }
}
