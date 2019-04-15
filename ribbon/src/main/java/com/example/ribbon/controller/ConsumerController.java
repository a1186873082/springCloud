package com.example.ribbon.controller;

import com.example.ribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Autowired
    private HelloService helloService;

    /**
     * 服务的发现
     * @return
     */
    @RequestMapping(value = "/ribbon_consumer", method = RequestMethod.GET)
    public String ribbonConsumer(){
        return helloService.helloService();
    }
}
