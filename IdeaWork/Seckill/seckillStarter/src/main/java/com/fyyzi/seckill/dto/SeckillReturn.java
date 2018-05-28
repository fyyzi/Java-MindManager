package com.fyyzi.seckill.dto;

/**
 * 封装返回类型结果
 *
 * @param <T>
 */
public class SeckillReturn<T> {

    private boolean success;

    private T data;

    private String errorMessage;

    public SeckillReturn(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public SeckillReturn(boolean success, String errorMessage) {
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
