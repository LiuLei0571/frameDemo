package com.lenny.framedemo.project.home.presenter;

import com.lenny.framedemo.common.http.IResult;
import com.lenny.framedemo.compent.base.BasePresenter;
import com.lenny.framedemo.compent.base.IView;
import com.lenny.framedemo.compent.thread.ApiTask;
import com.lenny.framedemo.project.home.activity.MainActivity;
import com.lenny.framedemo.project.home.manager.HomeManager;
import com.lenny.framedemo.project.home.manager.bean.ModelPage;

import java.util.List;

import javax.inject.Inject;

/**
 * 用途：
 * Created by milk on 17/4/19.
 * 邮箱：649444395@qq.com
 */

public class HomePresenter extends BasePresenter<MainActivity> {
    @Inject
    HomeManager manager;
    String mal;

    @Inject
    public HomePresenter(IView iView) {
        super(iView);
    }

    public void loadData() {
        submitTask("taskGroup", new ApiTask<List<ModelPage>>(getILoading()) {
            @Override
            public IResult<Object> onBackGround() throws Exception {
                return manager.home();
            }

            @Override
            public void onSuccess(IResult<List<ModelPage>> result) {
                super.onSuccess(result);
                if (result.data() != null) {
                    getView().showData(result.data().get(1).toString());

                }
            }
        });
    }
}
