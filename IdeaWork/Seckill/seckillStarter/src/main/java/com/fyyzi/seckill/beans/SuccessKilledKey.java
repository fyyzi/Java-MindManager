package com.fyyzi.seckill.beans;

public class SuccessKilledKey {
    private Long seckillId;

    private Long userPhone;

    private Seckill seckill;

    public SuccessKilledKey() {
    }

    public SuccessKilledKey(Long seckillId, Long userPhone) {
        this.seckillId = seckillId;
        this.userPhone = userPhone;
    }

    public SuccessKilledKey(Long seckillId, Long userPhone, Seckill seckill) {
        this.seckillId = seckillId;
        this.userPhone = userPhone;
        this.seckill = seckill;
    }

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    public Long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    public Long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }

    @Override
    public String toString() {
        return "SuccessKilledKey{" +
                "seckillId=" + seckillId +
                ", userPhone=" + userPhone +
                ", seckill=" + seckill +
                '}';
    }
}