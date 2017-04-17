package com.lenny.framedemo.compent.base;

import android.os.Bundle;
import android.view.View;

import com.lenny.framedemo.common.task.IGroup;

/**
 * 用途：
 * Created by milk on 17/4/17.
 * 邮箱：649444395@qq.com
 */

public interface IView extends IGroup, IAct {
    int getRootLayoutId();

    void beforeViewBind(View rootView);

    void afterViewBind(Bundle saveInstanceState);

    void findViewId(int id);

    void bindView(View view);

    void unBindView();

    void savePresenter(BasePresenter presenter);
}
