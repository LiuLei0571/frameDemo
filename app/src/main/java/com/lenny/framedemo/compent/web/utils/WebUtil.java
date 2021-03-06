package com.lenny.framedemo.compent.web.utils;

import android.text.TextUtils;
import android.util.Log;

import com.lenny.framedemo.common.helper.JsonHelper;
import com.lenny.framedemo.common.utils.lang.Chars;
import com.lenny.framedemo.common.utils.lang.Strings;
import com.lenny.framedemo.compent.constants.Configs;
import com.lenny.framedemo.compent.web.WebResultsStorage;
import com.lenny.framedemo.compent.web.bean.WebCall;
import com.lenny.framedemo.compent.web.protocol.param.UriBean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 用途：
 * Created by milk on 17/1/16.
 * 邮箱：649444395@qq.com
 */

public class WebUtil {
    private static final String URL_PRE = "axd://";

    public static String buildJsUrl(WebCall webCall) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("javascript:try{");
        stringBuilder.append(buildJs_(webCall, true));
        stringBuilder.append(";}catch(e){e.error(e);};");
        return stringBuilder.toString();
    }

    private static String buildJs_(Object result, boolean needShortJs) {
        StringBuilder stringBuilder = new StringBuilder();
        if (result != null) {
            if (result instanceof WebCall) {
                WebCall webFunc = (WebCall) result;
                if (webFunc.pre != null) {
                    stringBuilder.append(webFunc.pre);
                }
                stringBuilder.append(webFunc.func);
                Object[] args = webFunc.args;
                stringBuilder.append('(');
                if (args != null) {
                    int j = 0;
                    for (Object arg : args) {
                        stringBuilder.append(buildJs_(arg, needShortJs));
                        j++;
                        if (j != args.length) {
                            stringBuilder.append(Chars.COMMA);
                        }
                    }
                }
                stringBuilder.append(')');
                if (webFunc.ext != null) {
                    stringBuilder.append(webFunc.ext);
                }
            } else {
                String content;
                boolean needEval = false;
                if (result instanceof String) {
                    content = (String) result;
                } else {
                    content = JsonHelper.toJSONString(result);
                    Class cls = result.getClass();
                    if (!cls.isPrimitive()) {
                        needEval = true;
                    }
                }
                if (content.length() > Configs.MAX_H5_ARG_LENGTH && needShortJs) {
                    String key = WebResultsStorage.put(content);
                    WebCall webCall = WebCall.newWebCall(Configs.JS_INTERFACE_GETRESULT, key);
                    if (needEval) {
                        webCall.eval();
                    }
                    content = buildJs_(webCall, false);
                    stringBuilder.append(content);
                } else if (result instanceof String) {
                    stringBuilder.append(Chars.SIGLE_QUOTE).append(content)
                            .append(Chars.SIGLE_QUOTE);
                } else {
                    stringBuilder.append(content);
                }
            }
        } else {
            stringBuilder.append(Chars.SIGLE_QUOTE).append(Chars.SIGLE_QUOTE);
        }
        return stringBuilder.toString();
    }

    /**
     * 调整url
     *
     * @param url
     * @return
     */
    public static String addInfoToUrl(String url) {
        Log.d("url1 : ", url);
        String urlLower = url.toLowerCase();
        if (urlLower.startsWith("http")) {
            String uid = Strings.NULL_STR;
            String sign = Strings.NULL_STR;
            // 判断登录信息,如果登录的话拼接URL
//            if (UserHelper.isLogin()) {
//                User user = UserHelper.getUser();
//                uid = user.getUserId();
//                sign = user.getSign();
//            }
            if (url.contains("?")) {
                if (!url.contains("&uid=") && !url.contains("?uid=")) {
                    url = url + "&uid=" + uid;
                }
                if (!url.contains("&sign=") && !url.contains("?sign=")) {
                    url = url + "&sign=" + sign;
                }
            } else {
                url = url + "?uid=" + uid + "&sign=" + sign;
            }
        }
        Log.d("url2 : ", url);
        return url;
    }

    public static boolean isNormalUrl(String url) {
        if (url.startsWith("http://") || url.startsWith("https://")||url.startsWith("file:///")) {
            return true;
        }
        return false;
    }

    public static UriBean buildUriBean(String url) {
        if (!url.startsWith(URL_PRE)) {
            return null;
        }
        int q = url.indexOf("?");
        String moduleAction;
        String params = null;
        if (q != -1) {
            moduleAction = url.substring(URL_PRE.length(), q);
            params = url.substring(q + 1);
        } else {
            moduleAction = url.substring(URL_PRE.length());
        }
        String[] moduleActionArr = moduleAction.split("/");
        String module = null;
        String action = null;
        if (moduleActionArr.length >= 2) {
            module = moduleActionArr[0];
            action = moduleActionArr[1];
        } else {
            action = moduleActionArr[0];
        }
        if (TextUtils.isEmpty(action)) {
            return null;
        }
        return UriBean.newUriBean(module, action, params);
    }

    public static Type getSuperClassParam(Class cls) {
        Type superClassType = cls.getGenericSuperclass();
        if (superClassType != null && superClassType instanceof ParameterizedType) {
            ParameterizedType parameterized = (ParameterizedType) superClassType;
            Type[] args = parameterized.getActualTypeArguments();
            if (args != null && args.length > 0) {
                return args[0];
            }
        }
        return null;
    }

    public static String refresh(String reloadUrl) {
        return null;
    }
}
