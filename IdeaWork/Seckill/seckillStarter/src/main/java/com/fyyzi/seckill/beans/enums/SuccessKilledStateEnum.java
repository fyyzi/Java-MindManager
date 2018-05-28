package com.fyyzi.seckill.beans.enums;

/**
 * SuccessKilled表 state字段对应枚举类
 *
 * @author 羽化荼 2018年5月26日
 */
public enum SuccessKilledStateEnum {

    INVALID(-1, "无效"), SUCCESSFUL(0, "成功"), PAYMENT_HAS_BEEN(1, "已付款"), SHIPPED(2, "已发货");

    private Byte state;
    private String stateName;

    private SuccessKilledStateEnum(Byte state, String stateName) {
        this.state = state;
        this.stateName = stateName;
    }

    private SuccessKilledStateEnum(int state, String stateName) {
        this.state = (byte) state;
        this.stateName = stateName;
    }

    public Byte getState() {
        return state;
    }

    public String getStateName() {
        return stateName;
    }

    /**
     * 根据状态码获取对应枚举类
     * @param state
     * @return
     */
    public static SuccessKilledStateEnum stateOf(Byte state){
        SuccessKilledStateEnum[] values = SuccessKilledStateEnum.values();
        for (SuccessKilledStateEnum stateEnum : values) {
            boolean equals = stateEnum.getState().equals(state);
            if(equals){
                return stateEnum;
            }
        }
        return null;
    }

}
