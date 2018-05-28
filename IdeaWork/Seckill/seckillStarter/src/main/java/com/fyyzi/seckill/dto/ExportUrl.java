package com.fyyzi.seckill.dto;

/**
 * 暴露秒杀地址DTO
 */
public class ExportUrl {

    /**
     * 秒杀是否开启
     */
    private boolean exported;

    /**
     * 加密
     */
    private String md5;

    /**
     * 被秒杀商品的ID
     */
    private long seckillId;

    /**
     * 系统当前时间
     */
    private long nowTime;

    /**
     * 商品秒杀开启时间
     */
    private long startTime;

    /**
     * 商品秒杀结束时间
     */
    private long endTime;

    public ExportUrl(boolean exported, String md5, long endTime) {
        this.exported = exported;
        this.md5 = md5;
        this.endTime = endTime;
    }

    public ExportUrl(boolean exported,long seckillId, long nowTime, long startTime, long endTime) {
        this.exported = exported;
        this.seckillId = seckillId;
        this.nowTime = nowTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ExportUrl(boolean exported, long seckillId) {
        this.exported = exported;
        this.seckillId = seckillId;
    }


    public boolean isExported() {
        return exported;
    }

    public void setExported(boolean exported) {
        this.exported = exported;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getNowTime() {
        return nowTime;
    }

    public void setNowTime(long nowTime) {
        this.nowTime = nowTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "ExportUrl{" +
                "exported=" + exported +
                ", md5='" + md5 + '\'' +
                ", seckillId=" + seckillId +
                ", nowTime=" + nowTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
