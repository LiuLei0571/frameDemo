package com.lenny.framedemo.project.home;

import android.os.AsyncTask;

import com.lenny.framedemo.common.http.IResponse;
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
    String mal;

    @Inject
    public HomePresenter(IView iView) {
        super(iView);
    }

    public void loadData(String names) {
        new AsyncTask<String, Void, IResponse>() {
            @Override
            protected IResponse doInBackground(String... params) {
                manager.home();
                return null;
            }

            @Override
            protected void onPostExecute(IResponse iResponse) {
                super.onPostExecute(iResponse);
            }
        }.execute();
        //        getView().showData(manager.home()!=null?"success":"false");

    }
}
