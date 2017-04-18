package com.lenny.framedemo.common.parse.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.lenny.framedemo.common.parse.IParse;

import java.lang.reflect.Type;

/**
 * 用途：
 * Created by milk on 17/4/18.
 * 邮箱：649444395@qq.com
 */

public class FastJsonParse implements IParse {
    @Override
    public String toJson(Object bean) {
        if (bean != null) {
            try{
                return JSON.toJSONString(bean);
            }catch(Exception e){

            }
        }
        return null;
    }

    @Override
    public <T> T fromJson(String json, Class<T> clazz) {
        if (json != null) {
            try{
                return JSON.parseObject(json,clazz, Feature.InitStringFieldAsEmpty);
            }catch(Exception e){

            }
        }
        return null;
    }

    @Override
    public <T> T fromJson(String json, Type type) {
        if (json != null) {
            try{
                return JSON.parseObject(json,type);
            }catch(Exception e){

            }
        }
        return null;
    }
}
