package com.lenny.framedemo.compent.http;

import android.text.TextUtils;

import com.lenny.framedemo.common.http.impl.okhttp3.CookieIterator;
import com.lenny.framedemo.common.http.impl.okhttp3.EncodeUtil;
import com.lenny.framedemo.common.http.impl.okhttp3.ICookieStore;
import com.lenny.framedemo.common.utils.lang.Strings;
import com.lenny.framedemo.compent.constants.Sps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

/**
 * Created by yh on 2016/4/21.
 */
public class PersistentCookieStoreNew implements ICookieStore {
    protected final Map<String, ConcurrentHashMap<String, Cookie>> cookies;
    private Sps cookiePrefs;

    public PersistentCookieStoreNew() {
        cookiePrefs = Sps.cookie;
        cookies = new HashMap<>();
        init();
    }

    @Override
    public void iteratorCookies(CookieIterator cookieIterator) {
        Iterator<Map.Entry<String, ConcurrentHashMap<String, Cookie>>> it = cookies.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, ConcurrentHashMap<String, Cookie>> entry = it.next();
            String host = entry.getKey();
            ConcurrentHashMap<String, Cookie> cookieMap = entry.getValue();
            Collection<Cookie> cookieCollection = cookieMap.values();
            String str = TextUtils.join(Strings.COMMA, cookieCollection);
            cookieIterator.iterator(host, str);
        }
    }

    private void init() {
        //将持久化的cookies缓存到内存中 即map cookies
        Map<String, ?> prefsMap = cookiePrefs.getAll();
        for (Map.Entry<String, ?> entry : prefsMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (key.contains(Strings.AT) || !(value instanceof String)) {
                continue;
            }
            String[] cookieNames = TextUtils.split((String) entry.getValue(), Strings.COMMA);
            for (String name : cookieNames) {
                String encodedCookie = cookiePrefs.getString(key, name);
                Cookie cookie = EncodeUtil.decodeCookie(encodedCookie);
                if (cookie == null || isCookieExpires(cookie)) {//过期
                    continue;
                }
                ConcurrentHashMap<String, Cookie> cookieMap;
                if (!cookies.containsKey(key)) {
                    cookieMap = new ConcurrentHashMap();
                    cookies.put(key, cookieMap);
                } else {
                    cookieMap = cookies.get(key);
                }
                cookieMap.put(name, cookie);
            }
        }
    }

    protected String getCookieToken(Cookie cookie) {
        return cookie.name() + Strings.AT + cookie.domain();
    }

    public void add(HttpUrl url, Cookie cookie) {
        add(url.host(), cookie);
    }

    public void add(String urlHost, Cookie cookie) {
        String cookieKey = getCookieToken(cookie);

        ConcurrentHashMap<String, Cookie> cookieMap;
        //将cookies缓存到内存中 如果缓存过期 就重置此cookie
        if (!cookie.persistent()) {//未过期
            if (!cookies.containsKey(urlHost)) {
                cookieMap = new ConcurrentHashMap();
                cookies.put(urlHost, cookieMap);
            } else {
                cookieMap = cookies.get(urlHost);
            }
            cookieMap.put(cookieKey, cookie);
        } else {//已经过期
            if (cookies.containsKey(urlHost)) {
                cookieMap = cookies.get(urlHost);
                cookieMap.remove(cookieKey);
            } else {
                cookieMap = null;
            }
        }

        if (cookieMap != null && !cookieMap.isEmpty()) {
            cookiePrefs.putString(urlHost, TextUtils.join(Strings.COMMA, cookieMap.keySet()));
            cookiePrefs.putString(cookieKey, EncodeUtil.encodeCookie(cookie));
        } else {//清空
            cookiePrefs.remove(urlHost);
            cookiePrefs.remove(cookieKey);
        }
    }

    public List<Cookie> get(HttpUrl url) {
        return get(url.host());
    }

    public List<Cookie> get(String urlHost) {
        if (cookies.containsKey(urlHost)) {
            Collection<Cookie> cookieList = cookies.get(urlHost).values();
            return filterCookies(cookieList);
        }
        return null;
    }

    private List<Cookie> filterCookies(Collection<Cookie> cookieList) {
        ArrayList<Cookie> ret = null;
        if (cookieList != null) {
            ret = new ArrayList<>();
            for (Cookie cookie : cookieList) {
                if (!isCookieExpires(cookie)) {//未过期
                    ret.add(cookie);
                }
            }
        }
        return ret;
    }

    public boolean isCookieExpires(Cookie cookie) {
        long current = System.currentTimeMillis();
        if (current < cookie.expiresAt()) {//未过期
            return false;
        }
        return true;
    }

    public boolean removeAll() {
        cookiePrefs.removeAll();
        cookies.clear();
        return true;
    }

    public boolean remove(HttpUrl url, Cookie cookie) {
        return remove(url.host(), cookie);
    }

    public boolean remove(String urlHost, Cookie cookie) {
        String name = getCookieToken(cookie);
        if (cookies.containsKey(urlHost) && cookies.get(urlHost).containsKey(name)) {
            cookies.get(urlHost).remove(name);
            cookiePrefs.remove(name);
            cookiePrefs.putString(urlHost, TextUtils.join(Strings.COMMA, cookies.get(urlHost).keySet()));

            return true;
        } else {
            return false;
        }
    }

    public void remove(String host) {
        cookies.remove(host);
        String cookieNameStr = cookiePrefs.getString(key, host);
        if (cookieNameStr != null) {
            String[] cookieNameArr = TextUtils.split(cookieNameStr, Strings.COMMA);
            for (String cookieName : cookieNameArr) {
                cookiePrefs.remove(cookieName);
            }
            cookiePrefs.remove(host);
        }
    }
}
