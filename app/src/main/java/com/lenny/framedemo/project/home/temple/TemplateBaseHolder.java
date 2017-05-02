package com.lenny.framedemo.project.home.temple;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lenny.framedemo.compent.base.BaseHolder;


/**
 * Created by yh on 2016/10/26.
 */

public class TemplateBaseHolder extends BaseHolder {
    public TemplateBaseHolder(ViewGroup parent, @LayoutRes int resId) {
        super(LayoutInflater.from(parent.getContext()).inflate(resId, parent, false));
    }

    public TemplateBaseHolder(View view) {
        super(view);
    }

    public void onViewRecycled() {

    }

}
