package com.lenny.framedemo.project.home;

import com.lenny.framedemo.common.helper.ThreadHelper;
import com.lenny.framedemo.compent.base.BasePresenter;
import com.lenny.framedemo.compent.base.IView;
import com.lenny.framedemo.project.MainActivity;
import com.lenny.framedemo.project.home.manager.HomeManager;

import javax.inject.Inject;

/**
 * 用途：
 * Created by milk on 17/4/19.
 * 邮箱：649444395@qq.com
 */

public class HomePresenter extends BasePresenter<MainActivity> {
    @Inject
    HomeManager manager;
    @Inject
    public HomePresenter(IView iView) {
        super(iView);
    }

    public void loadData(String names) {
        ThreadHelper.postMainDelay(new Runnable() {
            @Override
            public void run() {
                getView().showData(manager.home().toString());

            }
        }, 3000);
    }
}
