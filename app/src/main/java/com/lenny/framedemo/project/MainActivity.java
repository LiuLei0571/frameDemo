package com.lenny.framedemo.project;

import android.os.Bundle;
import android.widget.TextView;

import com.lenny.framedemo.R;
import com.lenny.framedemo.compent.base.TempleActivity;
import com.lenny.framedemo.compent.cdi.cmp.ActivityComponent;
import com.lenny.framedemo.project.home.HomePresenter;

import javax.inject.Inject;

import butterknife.Bind;

public class MainActivity extends TempleActivity {
    @Inject
    HomePresenter presenter;
    @Bind(R.id.tv)
    TextView mTv;

    @Override
    protected int getRootViewId() {
        return R.layout.activity_main;
    }


    @Override
    protected void doInject(ActivityComponent activityComponent) {
        activityComponent.plus(this);
    }

    @Override
    public void afterViewBind(Bundle saveInstanceState) {
        super.afterViewBind(saveInstanceState);
        presenter.loadData("搞起来");
    }

    public void showData(String names) {
        mTv.setText(names);
    }
}
