package com.lenny.framedemo.compent.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * 用途：
 * Created by milk on 17/4/17.
 * 邮箱：649444395@qq.com
 */

public interface IAct {
    Context getContext();

    Activity getActivity();

    BaseActivity getBaseActivity();

    void startActivity(Intent intent);

    void startActivityForResult(Intent intent, int requestCode);
}
