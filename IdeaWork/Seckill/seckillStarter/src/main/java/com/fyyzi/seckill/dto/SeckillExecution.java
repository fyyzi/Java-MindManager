package com.fyyzi.seckill.dto;

import com.fyyzi.seckill.beans.SuccessKilled;
import com.fyyzi.seckill.enums.SeckillStateEnum;

/**
 * 封装秒杀执行后的结果
 */
public class SeckillExecution {

    /**
     * 秒杀对象ID
     */
    private long seckillId;

    /**
     * 秒杀执行结果状态
     */
    private int state;

    /**
     * 秒杀执行结果信息表示
     */
    private String stateInfo;

    /**
     * 秒杀成功对象
     */
    private SuccessKilled successKilled;

    public SeckillExecution(long seckillId, SeckillStateEnum seckillStateEnum, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = seckillStateEnum.getState();
        this.stateInfo = seckillStateEnum.getStateInfo();
        this.successKilled = successKilled;
    }

    public SeckillExecution(long seckillId, SeckillStateEnum seckillStateEnum) {
        this.seckillId = seckillId;
        this.state = seckillStateEnum.getState();
        this.stateInfo = seckillStateEnum.getStateInfo();
    }

    public long getSeckillId() {
        return seckillId;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }
}
