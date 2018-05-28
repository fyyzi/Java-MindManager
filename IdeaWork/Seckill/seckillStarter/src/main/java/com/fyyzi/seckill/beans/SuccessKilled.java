package com.fyyzi.seckill.beans;

import com.fyyzi.seckill.beans.enums.SuccessKilledStateEnum;
import com.fyyzi.seckill.enums.SeckillStateEnum;

import java.util.Date;

public class SuccessKilled extends SuccessKilledKey {

    private SuccessKilledStateEnum state = null;
//    private Byte state;

    private Date createTime;

    public SuccessKilled() {
    }

    public SuccessKilled(long seckillId, long userPhone) {
        super(seckillId,userPhone);
    }

    public Byte getState() {
        if (state == null){
            return null;
        }
        return state.getState();
    }

    public void setState(Byte state) {

        SuccessKilledStateEnum successKilledStateEnum = SuccessKilledStateEnum.stateOf(state);
        this.state = successKilledStateEnum;
        // 若无对应状态则执行相应业务逻辑
    }

    public SuccessKilledStateEnum getStateEnum(){
        return this.state;
    }

    public void setStateEnum(SuccessKilledStateEnum successKilledStateEnum){
        this.state = successKilledStateEnum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "state=" + state +
                ", createTime=" + createTime +
                "} " + super.toString();
    }
}