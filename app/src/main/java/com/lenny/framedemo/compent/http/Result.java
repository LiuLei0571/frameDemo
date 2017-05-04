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
    private Type resultType;
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


    public Result setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public Result setMsg(String msg) {
        this.msg = msg;
        return this;
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

    public Result setMeta(ErrorInfo meta) {
        this.meta = meta;
        return this;
    }



    public Type getResultType() {
        return resultType;
    }

    public Result setResultType(Type resultType) {
        this.resultType = resultType;
        return this;
    }


    @Override
    public Object data() {
        return data;
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public String msg() {
        return msg;
    }

    @Override
    public ErrorInfo errorInfo() {
        return meta;
    }
}
