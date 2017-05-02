package com.lenny.framedemo.compent.base;

import com.lenny.framedemo.common.http.IResult;
import com.lenny.framedemo.common.utils.lang.Maps;
import com.lenny.framedemo.compent.helper.HttpHelper;
import com.lenny.framedemo.compent.http.Api;

import java.util.HashMap;
import java.util.Map;

/**
 * 用途：
 * Created by milk on 17/4/20.
 * 邮箱：649444395@qq.com
 */

public abstract class BaseManager {
    protected <T> IResult<T> execute(Api api) {
        return HttpHelper.execute(api, Maps.mapNull);
    }

    protected <T> IResult<T> execute(Api api, Object object) {
        return HttpHelper.execute(api, object);
    }

    protected Map<String, Object> getHashMap() {
        return new HashMap<>();
    }
}
