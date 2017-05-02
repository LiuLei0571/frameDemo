package com.lenny.framedemo.common.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 用途：
 * Created by milk on 17/4/21.
 * 邮箱：649444395@qq.com
 */

public abstract class HttpScheduler {
    private HttpResultParse mHttpResultParse;
    private Map<String, Map<String, ICall>> callPool;
    private IRequestListener mIRequestListener;

    public HttpScheduler(HttpResultParse httpResultParse) {
        mHttpResultParse = httpResultParse;
    }

    public void setHttpResultParse(HttpResultParse httpResultParse) {
        mHttpResultParse = httpResultParse;
    }

    public void setIRequestListener(IRequestListener IRequestListener) {
        mIRequestListener = IRequestListener;
    }

    public IResult exexute(ICall iCall, String groupName, String taskName) {
        if (mIRequestListener != null) {
            mIRequestListener.beforeRequest(iCall);
        }
        IResult iResult = null;
        IResponse response = null;
        if (groupName != null && taskName != null) {
            Map<String, ICall> calls = callPool.get(groupName);
            if (calls == null) {
                calls = new HashMap<>();
                callPool.put(groupName, calls);
            }
            //向请求队列添加请求
            calls.put(taskName, iCall);
            try {
                response = doExecute(iCall);
            } catch (Exception e) {
                iResult = mHttpResultParse.onException(iCall, e);
            }
            calls.remove(taskName);
        } else {
            try {
                response = doExecute(iCall);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (iResult == null && response != null) {
            IApi api = iCall.getRequest().getAPi();
            iResult = mHttpResultParse.parseResult(response.getBody(), api);
        }
        if (mIRequestListener != null) {
            mIRequestListener.afterRequest(iCall, response);
        }
        return iResult;
    }
    public String getResult(ICall iCall, String groupName, String taskName){
        if (mIRequestListener != null) {
            mIRequestListener.beforeRequest(iCall);
        }
        String iResult=null;
        IResponse response=null;
        if (groupName != null && taskName != null) {
            Map<String, ICall> calls = callPool.get(groupName);
            if (calls == null) {
                calls = new HashMap<>();
                callPool.put(groupName, calls);
            }
            //向请求队列添加请求
            calls.put(taskName, iCall);
            try {
                response = doExecute(iCall);
            } catch (Exception e) {
            }
            calls.remove(taskName);
        } else {
            try {
                response = doExecute(iCall);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (iResult == null && response != null) {
            IApi api = iCall.getRequest().getAPi();
            Type type=api.getResultType();
            iResult =  response.getBody();
        }
        if (mIRequestListener != null) {
            mIRequestListener.afterRequest(iCall, response);
        }
        return iResult;
    }
    protected abstract IResponse doExecute(ICall iCall) throws Exception;

    public abstract ICall newCall(IRequest httpRequest);

    public void cancelGroup(String groupName) {
        Map<String, ICall> calls = callPool.get(groupName);
        if (calls != null) {
            Set<Map.Entry<String, ICall>> entrySet = calls.entrySet();
            for (Map.Entry<String, ICall> entry : entrySet) {
                ICall call = entry.getValue();
                call.cancel();
            }
            callPool.remove(groupName);
        }
    }

    public void downLoad(ICall iCall, File outFile) {
        if (outFile == null) {
            return;
        }
        FileOutputStream fos = null;
        InputStream inputStream = null;
        try {
            IResponse response = doExecute(iCall);
            inputStream = response.getInputStream();
            fos = new FileOutputStream(outFile);
            byte[] buf = new byte[2048];
            int len;
            while ((len = inputStream.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();

        } catch (Exception e) {

        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
            }
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {

            }
        }

    }
}
