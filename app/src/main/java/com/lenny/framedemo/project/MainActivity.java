package com.lenny.framedemo.project;

import android.os.Bundle;

import com.lenny.framedemo.R;
import com.lenny.framedemo.compent.base.TempleActivity;
import com.lenny.framedemo.compent.cdi.cmp.ActivityComponent;

public class MainActivity extends TempleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected int getRootViewId() {
        return R.layout.activity_main;
    }


    @Override
    protected void doInject(ActivityComponent activityComponent) {
        activityComponent.plus(this);
    }


}
