package com.benson.dto;

/**
 * VO，封装结果
 *
 * @author yaz
 * @create 2016-08-03 18:03
 */

public class SeckillResult<T> {
    private boolean success;
    private T data;
    private String error;

    public SeckillResult(String error, boolean success) {
        this.error = error;
    }

    public SeckillResult(T data, boolean success) {

        this.data = data;
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

