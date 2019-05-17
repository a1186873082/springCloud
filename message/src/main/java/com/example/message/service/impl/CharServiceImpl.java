package com.example.message.service.impl;

import com.example.message.mapper.model.Message;
import com.example.message.service.CharService;
import netscape.javascript.JSObject;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat")//标记为服务端
public class CharServiceImpl implements CharService {

    //全部在线会话
    public static Map<String, Session> onlineSession = new ConcurrentHashMap<>();

    /**
     * 客户端打开链接 1，添加会话对象  2，更新在线人数
     * @param session
     */
    @OnOpen
    public void opOpen(Session session){
        onlineSession.put(session.getId(), session);

    }

    @OnMessage
    public void onMessage(Session session, String jsonStr){

    }

}
