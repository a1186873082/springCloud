package com.example.feignconsumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "user",fallback = FeignConsumerProxyFallBack.class)
public interface FeignConsumerProxy {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello();

    @RequestMapping(value = "/text_proxy", method = RequestMethod.GET)
    public String textProxy();

    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    public String hello1(@RequestParam("userName") String userName, @RequestParam("fullName") String fullName);

    @RequestMapping(value = "/set_user", method = RequestMethod.POST)
    public String setUser(@RequestParam("userName") String userName, @RequestParam("fullName") String fullName);
}
