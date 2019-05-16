package com.example.message.config;

import com.example.message.handler.MyWebSocketHandler;
import com.example.message.interceptor.HandShake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

import javax.annotation.Resource;
import javax.websocket.MessageHandler;

@Configuration
@EnableWebSocketMessageBroker
@EnableScheduling
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket_address").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic","/queue"); //设置服务器广播消息的基础路径，可以是多个
        registry.setApplicationDestinationPrefixes("/app"); //设置客户端订阅消息的基础路径
        registry.setUserDestinationPrefix("/queue"); //设置精准消息推送基础路径

    }
}
