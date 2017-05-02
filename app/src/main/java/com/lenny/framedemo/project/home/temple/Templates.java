package com.lenny.framedemo.project.home.temple;


import com.lenny.framedemo.compent.base.IAct;
import com.lenny.framedemo.project.home.temple.impl.LocalEmptyTemplate;

import java.lang.reflect.Constructor;


/**
 * Created by yh on 2016/10/26.
 */

public enum Templates {
    LOCAL_EMPTY(LocalEmptyTemplate.class,false);

    Templates(Class<? extends ITemplate> templateCls) {
        this.templateCls = templateCls;
    }

    Templates(Class<? extends ITemplate> templateCls, boolean inOneRow) {
        this.templateCls = templateCls;
        this.inOneRow = inOneRow;
    }

    /**
     * 布局跨所有的列
     */
    private boolean inOneRow = true;
    private Class<? extends ITemplate> templateCls;

    public int getLocalViewType() {
        return ordinal();
    }

    public boolean isInOneRow() {
        return inOneRow;
    }

    /**
     * @param remoteViewType
     * @return
     */
    public static Class<? extends ITemplate> findViewMaker(String remoteViewType) {
        Templates template = null;
        try {
            template = valueOf(remoteViewType);
        } catch (Throwable throwable) {
        }
        if (template == null) {
            template = LOCAL_EMPTY;
        }
        return template.templateCls;
    }

    public static Templates findTemplate(String remoteViewType) {
        Templates template = null;
        try {
            template = valueOf(remoteViewType);
        } catch (Throwable throwable) {
        }
        if (template == null) {
            template = LOCAL_EMPTY;
        }else{
//            Logs.defaults.d("template name is %s", remoteViewType);
        }
        return template;
    }


    public static Class<? extends ITemplate> findViewMaker(int localViewType) {
        Templates template = null;
        try {
            template = Templates.values()[localViewType];
        } catch (Throwable throwable) {
        }
        if (template == null) {
            template = LOCAL_EMPTY;
        }
        return template.templateCls;
    }

    /**
     * 服务端的type转化为本地的type
     *
     * @param remoteViewType
     * @return
     */
    public static int findLocalViewType(String remoteViewType) {
        Templates template = null;
        try {
            template = valueOf(remoteViewType);
        } catch (Throwable throwable) {
        }
        if (template == null) {
            template = LOCAL_EMPTY;
        }
        return template.ordinal();
    }

    public static ITemplate makeInstance(IAct act, int localViewType) {
        ITemplate instance = null;
        Class<? extends ITemplate> cls = findViewMaker(localViewType);
        if (cls == null) {
            cls = LOCAL_EMPTY.templateCls;
        }
        try {
            Constructor<? extends ITemplate> constructor = cls.getDeclaredConstructor(IAct.class);
            instance = constructor.newInstance(act);
        } catch (Throwable throwable) {
        }
        return instance;
    }

}
