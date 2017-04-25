package com.lenny.framedemo.compent.http;

import com.lenny.framedemo.common.http.IResult;

import java.lang.reflect.Type;

/**
 * 用途：
 * Created by milk on 17/4/24.
 * 邮箱：649444395@qq.com
 */

public class Result<T> implements IResult {
    private T data;
    private ErrorInfo meta;
    private String errorInfo;
    private Type resultType;
    private int code;
    private boolean success;
    private String msg;


    public Result(T data, ErrorInfo meta) {
        this.data = data;
        this.meta = meta;
    }

    public static Result fail(String msg) {
        Result result = new Result();
        result.success = false;
        result.msg = msg;
        return result;
    }

    public boolean isSuccess() {
        return meta.isSuccess();
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return meta.getErrorInfo();
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Result() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ErrorInfo getMeta() {
        return meta;
    }

    public void setMeta(ErrorInfo meta) {
        this.meta = meta;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public Type getResultType() {
        return resultType;
    }

    public void setResultType(Type resultType) {
        this.resultType = resultType;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String code() {
        return null;
    }

    @Override
    public Object data() {
        return null;
    }

    @Override
    public boolean success() {
        return meta.isSuccess();
    }

    @Override
    public String json() {
        return null;
    }
}
