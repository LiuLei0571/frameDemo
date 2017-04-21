package com.lenny.framedemo.common.http.impl.okhttp3;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

/**
 * Created by milk on 2016/4/21.
 */
public interface ICookieStore {
    void add(HttpUrl url, Cookie item);

    List<Cookie> get(HttpUrl url);

    boolean removeAll();

    void remove(String host);

    void iteratorCookies(CookieIterator cookieIterator);

}
