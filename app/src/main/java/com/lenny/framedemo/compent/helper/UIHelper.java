package com.lenny.framedemo.compent.helper;

import com.lenny.framedemo.project.App;

/**
 * 用途：
 * 作者：Created by liulei on 17/5/5.
 * 邮箱：liulei2@aixuedai.com
 */


public class UIHelper {
    public static String getString(int textId) {
        return App.getContext().getString(textId);
    }
}
