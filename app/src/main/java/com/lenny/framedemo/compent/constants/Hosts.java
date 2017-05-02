package com.lenny.framedemo.compent.constants;

import com.lenny.framedemo.compent.http.IHost;

/**
 * 用途：
 * 作者：Created by liulei on 17/5/2.
 * 邮箱：liulei2@aixuedai.com
 */


public interface Hosts {
    IHost defaults = new IHost() {
        @Override
        public String getHost() {
            return Configs.BASE_URL;
        }
    };
    IHost express = new IHost() {
        @Override
        public String getHost() {
            return Configs.EXPRESS_URL;
        }
    };
    IHost url = new IHost() {
        @Override
        public String getHost() {
            return Configs.IMAGE_URL;
        }
    };
}
