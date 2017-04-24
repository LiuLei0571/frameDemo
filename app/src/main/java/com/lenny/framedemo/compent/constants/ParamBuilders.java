package com.lenny.framedemo.compent.constants;

import com.lenny.framedemo.compent.http.paramBuilder.NewBuilder;
import com.lenny.framedemo.compent.http.paramBuilder.NormalBuilder;

/**
 * 用途：
 * Created by milk on 17/4/24.
 * 邮箱：649444395@qq.com
 */

public interface ParamBuilders {
    NewBuilder news = new NewBuilder();
    NormalBuilder nomal = new NormalBuilder();
}
