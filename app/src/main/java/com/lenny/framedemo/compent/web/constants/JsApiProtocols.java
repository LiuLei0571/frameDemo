package com.lenny.framedemo.compent.web.constants;


import com.lenny.framedemo.compent.web.bean.ProtocolBean;
import com.lenny.framedemo.compent.web.protocol.impl.jsapi.tools.UploadPickImageExecute;
import com.lenny.framedemo.compent.web.protocol.impl.jsapi.tools.UploadTakePhotoExecute;

/**
 * 用途：
 * Created by milk on 17/3/10.
 * 邮箱：649444395@qq.com
 */

public interface JsApiProtocols {
    ProtocolBean uploadPickImage=ProtocolBean.buildProtocol(UploadPickImageExecute.class,"uploadWithPickImage").module("tools");
    ProtocolBean uploadWithTakePhoto=ProtocolBean.buildProtocol(UploadTakePhotoExecute.class,"uploadWithTakePhoto").module("tools");
}
