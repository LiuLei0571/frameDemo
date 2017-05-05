package com.lenny.framedemo.compent.web.presenter;


import android.os.Bundle;

import com.lenny.framedemo.R;
import com.lenny.framedemo.compent.base.BasePresenter;
import com.lenny.framedemo.compent.base.IView;
import com.lenny.framedemo.compent.web.view.activity.WebViewActivity;
import com.lenny.framedemo.compent.web.view.fragment.WebViewFragment;
import com.tencent.smtt.sdk.WebBackForwardList;
import com.tencent.smtt.sdk.WebHistoryItem;
import com.tencent.smtt.sdk.WebView;

import javax.inject.Inject;

/**
 * 用途：
 * Created by milk on 17/1/16.
 * 邮箱：649444395@qq.com
 */

public class WebViewActivityPresenter extends BasePresenter<WebViewActivity> {
    private WebViewFragment mWebViewFragment;
    public final String WEB_TYPE = "web_type";

    @Inject
    public WebViewActivityPresenter(IView iView) {
        super(iView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            mWebViewFragment = new WebViewFragment();

            mWebViewFragment.setArguments(getIntent().getExtras());
            getSupportsFragmentManager().beginTransaction().replace(R.id.container, mWebViewFragment, WEB_TYPE).commit();
        } else {
            mWebViewFragment = (WebViewFragment) getSupportsFragmentManager().findFragmentByTag(WEB_TYPE);
        }
    }

    public void backPress() {
        if (mWebViewFragment != null) {
            WebView webView = mWebViewFragment.getWebView();
            if (webView != null) {
                if (webView.canGoBack()) {
                    int backStep = -1;
                    WebBackForwardList list = webView.copyBackForwardList();
                    int size = list.getSize();
                    if (size >= 2) {
                        WebHistoryItem currentItem = list.getCurrentItem();
                        String currentUrl = currentItem.getUrl();
                        WebHistoryItem originItem = list.getItemAtIndex(list.getCurrentIndex() - 1);
                        String originUrl = originItem.getUrl();
//                        if (WebUtil.isMatch(currentUrl, originUrl)) {//符合规则的url则回退两步
//                            backStep = -2;
//                        }
                        if (list.getCurrentIndex() + 1 + backStep > 0) {//未超出历史范围则回退
                            webView.goBackOrForward(backStep);
                            return;
                        }
                    }
                }
            }
            closeWeb();
        }
    }

    public void closeWeb() {
        finish();

    }
}
