package com.lenny.framedemo.compent.web.constants;


import com.lenny.framedemo.compent.web.bean.ProtocolBean;
import com.lenny.framedemo.compent.web.protocol.impl.jsapi.home.HomeExecute;

/**
 * 用途：
 * Created by milk on 17/3/10.
 * 邮箱：649444395@qq.com
 */

public interface UrlProtocols {
    ProtocolBean homes=ProtocolBean.buildProtocol(HomeExecute.class,"go").module("home");
}
