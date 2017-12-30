package com.fyyzi.idealearning.jpa.Global.exceptions;

import com.fyyzi.idealearning.jpa.Global.enums.GirlResultEnum;

/**
 * Girl异常类别
 *
 * @author 息阳
 * Create in 22:18 2017/12/30
 * @version 1.0
 */
public abstract class GirlException extends RuntimeException {

    private Integer code;

    public GirlException(GirlResultEnum girlResultEnum) {
        super(girlResultEnum.getMessage());
        this.code = girlResultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
