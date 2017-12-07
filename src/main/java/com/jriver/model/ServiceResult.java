package com.jriver.model;

/**
 * Created by wujianjiang on 2017-12-7.
 */
public class ServiceResult<T> extends BaseModel {

    private String code;

    private String message;

    private T data;

    public ServiceResult() {
    }

    public ServiceResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
