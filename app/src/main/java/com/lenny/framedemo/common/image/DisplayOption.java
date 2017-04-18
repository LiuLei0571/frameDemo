package com.lenny.framedemo.common.image;

/**
 * 用途：
 * Created by milk on 17/4/18.
 * 邮箱：649444395@qq.com
 */

public class DisplayOption {
    public Integer loadingResId = -1;
    public Integer defaultResId = -1;
    public Integer errorResId = -1;
    public Integer maxWith;
    public Integer maxHeight = -1;
    public Boolean cacheMemory = true;
    public Boolean cacheOnDisk = true;

    public DisplayOption() {
    }

    public void setLoadingResId(Integer loadingResId) {
        this.loadingResId = loadingResId;
    }

    public DisplayOption setDefaultResId(Integer defaultResId) {
        this.defaultResId = defaultResId;
        return this;
    }

    public void setErrorResId(Integer errorResId) {
        this.errorResId = errorResId;
    }

    public void setMaxWith(Integer maxWith) {
        this.maxWith = maxWith;
    }

    public void setMaxHeight(Integer maxHeight) {
        this.maxHeight = maxHeight;
    }

    public void setCacheMemory(boolean cacheMemory) {
        this.cacheMemory = cacheMemory;
    }

    public void setCacheOnDisk(boolean cacheOnDisk) {
        this.cacheOnDisk = cacheOnDisk;
    }

    public static DisplayOption builder() {
        return new DisplayOption();
    }
}
