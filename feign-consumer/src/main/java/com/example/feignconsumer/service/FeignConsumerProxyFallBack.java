package com.example.feignconsumer.service;

import org.springframework.stereotype.Component;

@Component
public class FeignConsumerProxyFallBack implements FeignConsumerProxy{

    @Override
    public String hello() {
        System.out.println("失败时执行降级处理");
        return "降级";
    }

    @Override
    public String textProxy() {
        System.out.println("失败时执行降级处理");
        return "降级";
    }

    @Override
    public String hello1(String userName, String fullName) {
        System.out.println("失败时执行降级处理");
        return "降级";
    }

    @Override
    public String setUser(String userName, String fullName) {
        System.out.println("失败时执行降级处理");
        return "降级";
    }
}
