package com.lenny.framedemo.common.http;

/**
 * 用途：
 * Created by milk on 17/4/21.
 * 邮箱：649444395@qq.com
 */

public interface HttpResultParse {
    /**
     * 字符串解析成对象结果
     */
    IResult parseResult(String json, IApi api);

    IResult onException(ICall iCall, Exception e);
}
