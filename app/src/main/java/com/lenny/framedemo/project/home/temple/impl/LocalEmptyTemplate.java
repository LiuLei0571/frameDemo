package com.lenny.framedemo.project.home.temple.impl;

import android.view.View;
import android.view.ViewGroup;

import com.lenny.framedemo.compent.base.IAct;
import com.lenny.framedemo.project.home.temple.BaseTemplate;
import com.lenny.framedemo.project.home.temple.ITemplateModel;
import com.lenny.framedemo.project.home.temple.TemplateBaseHolder;


/**
 * Created by yh on 2016/10/27.
 */

public class LocalEmptyTemplate extends BaseTemplate<ITemplateModel, TemplateBaseHolder> {

    public LocalEmptyTemplate(IAct act) {
        super(act);
    }

    @Override
    public TemplateBaseHolder createCustomViewHolder(ViewGroup parent) {
        View emptyView = new View(parent.getContext());
        return new TemplateBaseHolder(emptyView);
    }
}
