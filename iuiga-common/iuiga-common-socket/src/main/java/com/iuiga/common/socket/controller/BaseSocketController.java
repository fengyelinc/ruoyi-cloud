package com.iuiga.common.socket.controller;

import com.iuiga.common.socket.annotation.*;
import com.iuiga.common.socket.domain.MyChannelHandlerMap;
import com.iuiga.common.socket.pojo.Session;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.timeout.IdleStateEvent;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.Map;

/**
 * socket通用数据处理
 *
 * @author xueyi
 */
public abstract class BaseSocketController {

    @BeforeHandshake
    public void handshake(Session session, HttpHeaders headers, @RequestParam("req") String req, @RequestParam("user") String user, @RequestParam("t") String t, @RequestParam MultiValueMap reqMap, @PathVariable String arg, @PathVariable Map pathMap) {
        session.setSubprotocols("stomp");
        if (!"ok".equals(req)) {
            System.out.println("Authentication failed!");
            session.close();
        }
    }

    @OnOpen
    public void onOpen(Session session, HttpHeaders headers,  @RequestParam("req") String req,@RequestParam("user") String user, @RequestParam("t") String t, @RequestParam MultiValueMap reqMap, @PathVariable String arg, @PathVariable Map pathMap) {
        System.out.println("new connection");
        MyChannelHandlerMap.put(user,session);
        handleOpen();
    }

    /**
     * 创建Netty连接时操作
     */
    public void handleOpen() {
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        System.out.println("one connection closed");
        handleClose();
    }

    /**
     * 关闭Netty连接时操作
     */
    public void handleClose() {

    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
        MyChannelHandlerMap.removeBySession(session);
        handleError();
    }

    /**
     * Netty连接出错报错
     */
    public void handleError() {

    }

    @OnMessage
    public void onMessage(Session session, String message) {
        System.out.println(message);
        session.sendText(message);
        handleMessage(message);
    }

    /**
     * Netty发送消息
     * @param msg
     */
    public void handleMessage(String msg) {

    }

    /**
     *
     * @param session
     * @param bytes
     */
    @OnBinary
    public void onBinary(Session session, byte[] bytes) {
        for (byte b : bytes) {
            System.out.println(b);
        }
        session.sendBinary(bytes);
    }

    /**
     * Netty文件流操作
     * @param bytes
     */
    public void handleBinary(byte[] bytes) {

    }

    @OnEvent
    public void onEvent(Session session, Object evt) {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            switch (idleStateEvent.state()) {
                case READER_IDLE:
                    System.out.println("read idle");
                    break;
                case WRITER_IDLE:
                    System.out.println("write idle");
                    break;
                case ALL_IDLE:
                    System.out.println("all idle");
                    break;
                default:
                    break;
            }
        }
        handleEvent(evt);
    }

    /**
     * Netty事件操作
     * @param evt
     */
    public void handleEvent(Object evt) {

    }
}