package com.fyyzi.idealearning.jpa.Global.enums;

/**
 * Girl最终返回值消息枚举类
 *
 * @author 息阳
 * Create in 23:07 2017/12/30
 * @version 1.0
 */
public enum GirlResultEnum {
    Success(0, "成功"),
    UNKNOW_ERROR(100, "未知错误"),
    GIRL_AGE_PRIMARY_ERROR(101, "age<10"),
    GIRL_AGE_MIDDLE_ERROR(102, "age>10&&age<16");

    private Integer code;
    private String message;

    GirlResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
