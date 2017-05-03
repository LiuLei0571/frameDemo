package com.lenny.framedemo.common.task;

/**
 * 用途：
 * 作者：Created by liulei on 17/5/3.
 * 邮箱：liulei2@aixuedai.com
 */


public interface ITaskBackGround<T> {
    <T> T onBackGround() throws Exception;
}
