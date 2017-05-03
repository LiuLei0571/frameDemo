package com.lenny.framedemo.compent.helper;

import com.lenny.framedemo.common.helper.ThreadHelper;
import com.lenny.framedemo.common.http.HttpScheduler;
import com.lenny.framedemo.common.http.ICall;
import com.lenny.framedemo.common.http.IRequest;
import com.lenny.framedemo.common.http.IResult;
import com.lenny.framedemo.common.thread.ThreadLocalHelper;
import com.lenny.framedemo.compent.http.Api;
import com.lenny.framedemo.compent.http.DemoHttpRequest;

/**
 * 用途：
 * Created by milk on 17/4/21.
 * 邮箱：649444395@qq.com
 */

public class HttpHelper {
    private static HttpScheduler sHttpScheduler;

    static {
        sHttpScheduler = CDIHelper.getInstance().mHttpScheduler;
    }

    public static <T> IResult<T> execute(Api api, Object params) {

        return execute(DemoHttpRequest.build(api).setParams(params));
    }

    private static <T> IResult<T> execute(IRequest demoHttpRequest) {
        ICall icall = sHttpScheduler.newCall(demoHttpRequest);
        ThreadLocalHelper.TaskInfo taskInfo = ThreadLocalHelper.getInfoThreadLocal();
        IResult<T> result = sHttpScheduler.exexute(icall, "at", "group");
        return result;
    }

    private static <T> IResult<T> executeString(IRequest demoHttpRequest) {
        ICall icall = sHttpScheduler.newCall(demoHttpRequest);
        ThreadLocalHelper.TaskInfo taskInfo = ThreadLocalHelper.getInfoThreadLocal();
        IResult<T> result = sHttpScheduler.exexute(icall, taskInfo.groupName, taskInfo.taskName);
        return result;
    }

    public static void cancelGroup(String groupName) {
        ThreadHelper.postMain(new Runnable() {
            @Override
            public void run() {
                sHttpScheduler.cancelGroup(groupName);
            }
        });
    }
}
