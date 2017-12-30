package com.fyyzi.idealearning.jpa.handlers;

import com.fyyzi.idealearning.jpa.Global.enums.GirlResultEnum;
import com.fyyzi.idealearning.jpa.domain.ResultModel;
import com.fyyzi.idealearning.jpa.Global.exceptions.GirlException;
import com.fyyzi.idealearning.jpa.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 息阳
 * Create in 22:41 2017/12/30
 * @version 1.0
 */
@ControllerAdvice
public class GirlErrorHandler {

    private final static Logger logger = LoggerFactory.getLogger(GirlErrorHandler.class);

    @ExceptionHandler(value = GirlException.class)
    @ResponseBody
    public ResultModel girlExceptionHandler(Exception e) {
        ResultModel resultModel;
        if (e instanceof GirlException) {
            GirlException girlExcept = (GirlException) e;
            resultModel = ResultUtils.error(GirlResultEnum.GIRL_AGE_PRIMARY_ERROR);
        } else {
            logger.info("【系统异常】{}", e.getMessage());
            resultModel = ResultUtils.error(GirlResultEnum.UNKNOW_ERROR);
        }

        return resultModel;
    }
}
