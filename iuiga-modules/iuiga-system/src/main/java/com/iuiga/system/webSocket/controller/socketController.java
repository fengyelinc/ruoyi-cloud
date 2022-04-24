package com.iuiga.system.webSocket.controller;

import com.iuiga.common.socket.controller.BaseSocketController;
import org.springframework.stereotype.Component;
import com.iuiga.common.socket.annotation.ServerEndpoint;
/**
 * socket通用数据处理
 *
 * @author xueyi
 */
@ServerEndpoint("/webSocket2")
@Component
public class socketController extends BaseSocketController {

    public void handleMessage(String message) {
        System.out.println("receive: msg:"+message);
    }
}