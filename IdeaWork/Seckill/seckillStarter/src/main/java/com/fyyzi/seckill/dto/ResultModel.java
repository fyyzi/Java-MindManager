package com.fyyzi.seckill.dto;

/**
 * Http请求返回的最外层对象
 * @param <T>
 * @author 羽化荼 2018年5月27日
 * @version 1.0
 */
public class ResultModel<T> {

    /**
     * 错误码
     */
    private Integer code = 0;

    /**
     * 提示信息
     */
    private String message = null;

    /**
     * 具体内容
     */
    private T data;

    public ResultModel() {
    }

    public ResultModel(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
