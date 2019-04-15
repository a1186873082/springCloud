package com.example.feignconsumer.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.feignconsumer.model.User;
//import com.example.feignconsumer.service.RefactorHelloService;
import com.example.feignconsumer.service.FeignConsumerProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class FeignConsumerController {

    @Autowired
    private FeignConsumerProxy feignConsumer;

    @Autowired
//    private RefactorHelloService refactorHelloService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        return feignConsumer.hello();
    }

    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    public String hello1(@ModelAttribute User params){
        String returnValue = feignConsumer.hello1(params.getUserName(), params.getFullName());
        return returnValue;
    }

    @RequestMapping(value = "/set_user", method = RequestMethod.POST)
    public String setUser(@RequestBody User params){
        return feignConsumer.hello1(params.getUserName(), params.getFullName());
    }

//    @RequestMapping(value = "/feign-consumer3", method = RequestMethod.GET)
//    public String feignConsumer3(){
//        StringBuffer stringBuffer = new StringBuffer();
//        stringBuffer.append(refactorHelloService.hello("MINI")).append("\n");
//        return stringBuffer.toString();
//    }
}
