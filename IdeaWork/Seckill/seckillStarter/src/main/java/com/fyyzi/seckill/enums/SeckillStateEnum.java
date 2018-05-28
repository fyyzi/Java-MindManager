package com.fyyzi.seckill.enums;

import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 秒杀状态枚举类
 */
public enum SeckillStateEnum {

    /**
     * 秒杀成功
     */
    END(0, "秒杀结束"),
    SUCCESS(1, "秒杀成功"), REPETA_KILL(-1, "重复秒杀"), INNER_ERROR(-2, "系统异常"), DATA_REWRITE(-3, "数据篡改");

    /**
     * 秒杀状态
     */
    int state;
    String stateInfo;

    SeckillStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    /**
     * 根据state状态吗获取对应枚举类
     * @param index
     * @return
     * @throws Exception
     */
    public static SeckillStateEnum stateOf(int index) throws Exception {
        for (SeckillStateEnum seckillStateEnum : SeckillStateEnum.values()) {
            if (seckillStateEnum.getState() == index) {
                return seckillStateEnum;
            }
        }
        // TODO 如果找不到对应枚举类 则执行相应的业务逻辑
        return null;
    }
}
