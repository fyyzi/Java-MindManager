package com.fyyzi.idealearning.jpa.Global.exceptions;

import com.fyyzi.idealearning.jpa.Global.enums.GirlResultEnum;

/**
 * Girl 年龄异常
 *
 * @author 息阳
 * Create in 22:20 2017/12/30
 * @version 1.0
 */
public class GirlAgeException extends GirlException {

    private Integer code;

    public GirlAgeException(GirlResultEnum girlResultEnum) {
        super(girlResultEnum);
        this.code = girlResultEnum.getCode();
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public void setCode(Integer code) {
        this.code = code;
    }
}
