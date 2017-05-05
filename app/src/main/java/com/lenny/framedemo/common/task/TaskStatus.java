package com.lenny.framedemo.common.task;

/**
 * Created by yh on 2016/4/20.
 */
public interface TaskStatus {
    /**
     * 开始
     * UI启动恢复被降级的任务
     */
    int STATUS_START = 0;
    /**
     * 结束
     * UI停止时降级其下的任务
     */
    int STATUS_STOP = 1;
    /**
     * 销毁
     * UI销毁时取消由其发起的所有任务
     */
    int STATUS_DESTROY = 2;
}
