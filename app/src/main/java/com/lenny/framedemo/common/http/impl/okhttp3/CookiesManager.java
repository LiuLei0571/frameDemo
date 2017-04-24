package com.lenny.framedemo.common.http.impl.okhttp3;

import android.text.TextUtils;
import android.webkit.CookieSyncManager;

import com.lenny.framedemo.common.util.lang.Strings;
import com.tencent.smtt.sdk.CookieManager;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by milk on 2016/4/21.
 */
public class CookiesManager implements CookieJar {
    private ICookieStore cookieStore;
    private CookieManager webCookieManager;
    private List<Cookie> empty = new ArrayList<>();
    private Pattern pattern = Pattern.compile("^*(.aixuedai.com|.aiyoumi.com)$");



    public ICookieStore getCookieStore() {
        return cookieStore;
    }

    public CookiesManager(ICookieStore cookieStore, CookieManager webCookieManager) {
        this.webCookieManager = webCookieManager;
        this.cookieStore = cookieStore;
        cookieStore.iteratorCookies(new CookieIterator() {
            @Override
            public void iterator(String urlHost, String cookieStr) {
                if (TextUtils.isEmpty(urlHost)) {
                    return;
                }
                webCookieManager.setCookie(urlHost, cookieStr);
            }
        });
        CookieSyncManager.getInstance().sync();
    }

    /**
     * 是否支持该host
     *
     * @param host
     * @return
     */
    private boolean supportHost(String host) {
        if (host == null) {
            return false;
        }
        Matcher m = pattern.matcher(host);
        return m.find();
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (url != null && cookies != null && !TextUtils.isEmpty(url.host())) {
            for (Cookie cookie : cookies) {
                if (cookie == null) {
                    continue;
                }
                String cookieStr = cookie.toString();
                if (TextUtils.isEmpty(cookieStr)) {
                    continue;
                }
                String host = url.host();
                webCookieManager.setCookie(host, cookieStr);
                cookieStore.add(url, cookie);
            }
            CookieSyncManager.getInstance().sync();
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        if (url == null) {
            return empty;
        }
        List<Cookie> cookies = cookieStore.get(url);
        if (cookies == null) {
            return empty;
        }
        return cookies;
    }

    public void syncCookie(String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        HttpUrl httpUrl = null;
        try {
            httpUrl = HttpUrl.parse(url);
        } catch (Exception e) {
        }
        if (httpUrl == null) {
            return;
        }
        if (supportHost(httpUrl.host())) {
            final HttpUrl httpUrlFinal = httpUrl;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    syncCookie(httpUrlFinal);
                }
            }).start();
        }
    }

    private synchronized void syncCookie(HttpUrl httpUrl) {
        String urlHost = httpUrl.host();
        cookieStore.remove(urlHost);//全部使用h5的cookie

        String cookiesStr = webCookieManager.getCookie(urlHost);
        if (cookiesStr != null) {
            String[] cookieArr = cookiesStr.split(Strings.SEMICOLON);
            if (cookieArr != null) {
                for (String cookieStr : cookieArr) {
                    Cookie cookie = null;
                    try {
                        cookie = Cookie.parse(httpUrl, cookieStr);
                    } catch (Exception e) {
                    }
                    if (cookie != null) {
                        cookieStore.add(httpUrl, cookie);
                    }
                }
            }
        }
    }


}