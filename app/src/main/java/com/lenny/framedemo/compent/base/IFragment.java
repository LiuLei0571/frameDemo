package com.lenny.framedemo.compent.base;

import com.lenny.framedemo.compent.cdi.cmp.FragmentComponent;

/**
 * 用途：
 * Created by milk on 17/4/17.
 * 邮箱：649444395@qq.com
 */

public interface IFragment extends IView {
    String getTrackRemark();

    void doInject(FragmentComponent component);
}
