package com.lenny.framedemo.compent.web.presenter;

import com.lenny.framedemo.compent.base.BasePresenter;
import com.lenny.framedemo.compent.base.IView;
import com.lenny.framedemo.compent.web.view.fragment.WebViewFragment;

import javax.inject.Inject;

/**
 * 用途：
 * 作者：Created by liulei on 17/5/5.
 * 邮箱：liulei2@aixuedai.com
 */


public class WebViewFragmentPresenter extends BasePresenter<WebViewFragment> {
    @Inject
    public WebViewFragmentPresenter(IView iView) {
        super(iView);
    }
}
