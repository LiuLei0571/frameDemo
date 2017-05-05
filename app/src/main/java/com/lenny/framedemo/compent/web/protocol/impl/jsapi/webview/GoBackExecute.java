package com.lenny.framedemo.compent.web.protocol.impl.jsapi.webview;


import com.lenny.framedemo.compent.web.WebInterface;
import com.lenny.framedemo.compent.web.callback.ICallBack;
import com.lenny.framedemo.compent.web.protocol.BaseProtocolInstance;
import com.tencent.smtt.sdk.WebView;


/**
 * 用途：
 * Created by milk on 17/3/17.
 * 邮箱：649444395@qq.com
 */

public class GoBackExecute extends BaseProtocolInstance {
    @Override
    public void doExecute(WebInterface iAct, ICallBack iCallBack, Object params) {
        WebView webView = iAct.getWebView();
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            iAct.getActivity().finish();
        }
    }
}
