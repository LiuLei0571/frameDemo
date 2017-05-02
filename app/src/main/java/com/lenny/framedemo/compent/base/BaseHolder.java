package com.lenny.framedemo.compent.base;


import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * 用途：
 * 作者：Created by liulei on 17/5/2.
 * 邮箱：liulei2@aixuedai.com
 */


public class BaseHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> viewSparseArray;

    public BaseHolder(View itemView) {
        super(itemView);
        viewSparseArray = new SparseArray<>();
    }

}
