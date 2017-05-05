package com.lenny.framedemo.compent.web;

import android.webkit.WebView;

import com.lenny.framedemo.compent.base.IAct;
import com.lenny.framedemo.compent.web.bean.WebCall;
import com.lenny.framedemo.compent.web.protocol.param.UriBean;

/**
 * 用途：
 * Created by milk on 17/1/16.
 * 邮箱：649444395@qq.com
 */

public interface WebInterface extends IAct {
    WebView getWebView();
    boolean callWeb(WebCall webCall);
    void doJsExecute(UriBean uriBean);
}
