package com.lenny.framedemo.common.http;

import java.io.IOException;
import java.io.InputStream;

/**
 * 用途：
 * Created by milk on 17/4/21.
 * 邮箱：649444395@qq.com
 */

public interface IResponse {
    String getBody();
    InputStream getInputStream() throws IOException;
}
