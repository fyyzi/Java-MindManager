package com.fyyzi.idealearning.jpa.utils;

import com.fyyzi.idealearning.jpa.Global.enums.GirlResultEnum;
import com.fyyzi.idealearning.jpa.domain.ResultModel;

/**
 * 处理返回值的工具类
 *
 * @author 息阳
 * Create in 21:53 2017/12/30
 * @version 1.0
 */
public class ResultUtils {

    /**
     * 若成功，则将返回对象包装在ResultModel中，并且返回成功码 0 message 为 “成功”
     *
     * @param object 方法成功时候返回的对象
     * @return ResultModel 对象
     */
    public static ResultModel success(Object object) {
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(0);
        resultModel.setMessage("成功");
        resultModel.setData(object);

        return resultModel;
    }

    /**
     * 返回值为null的情况
     *
     * @return ResultModel对象，等同于success(Object object)
     */
    public static ResultModel success() {
        return success(null);
    }

    /**
     * 若失败，则返回对应失败代码，并且ResultModel data属性为空
     * @param code
     * @param message
     * @return
     */
    public static ResultModel error(Integer code , String message) {
        ResultModel resultModel = new ResultModel();

        resultModel.setCode(code);
        resultModel.setMessage(message);
        return resultModel;
    }

    /**
     *
     * @param girlResultEnum
     * @return
     */
    public static ResultModel error(GirlResultEnum girlResultEnum) {
        return error(girlResultEnum.getCode(),girlResultEnum.getMessage());
    }
}
