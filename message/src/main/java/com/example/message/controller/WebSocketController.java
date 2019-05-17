package com.example.message.controller;

import com.example.message.mapper.model.AricMessage;
import com.example.message.mapper.model.AricResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.Request;

import java.security.Principal;
import java.util.Date;

@RestController
public class WebSocketController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    /**
     * webSocket控制器
     */
    @MessageMapping("/welcome") //当浏览器向服务端发送请求时,通过@MessageMapping映射/welcome这个地址,类似于@ResponseMapping
    @SendTo("/topic/getResponse")//当服务器有消息时,会对订阅了@SendTo中的路径的浏览器发送消息
    public AricResponse say(AricMessage message) {
        try {
            //睡眠1秒
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AricResponse("welcome," + message.getName() + "!"+ message.getMsg());
    }

//    @MessageMapping("/publicChat")
//    @SendTo("/topic/getResponse")
//    public AricResponse say1(AricMessage message){
//        StringBuilder msg=new StringBuilder();
//        msg.append(message.getName()).append(" -- ")
//                .append(new Date()).append("\n>>> ")
//                .append(message.getName()).append('\n');
//        AricResponse response=new AricResponse(msg.toString());
//        return response;
//    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public AricResponse test(@ModelAttribute AricMessage message){

        AricResponse aricResponse = new AricResponse(message.getName());
        simpMessagingTemplate.convertAndSend("/topic/getResponse", aricResponse);
        return aricResponse;
    }
}
