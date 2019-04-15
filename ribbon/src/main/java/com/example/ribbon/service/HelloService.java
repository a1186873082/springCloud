package com.example.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallback")
    public String helloService(){
        long start = System.currentTimeMillis();
        String returnValue = restTemplate.getForEntity("http://user/hello", String.class).getBody();
        System.out.println("Spend Time:" + (System.currentTimeMillis() - start));
        return returnValue;
    }

    public String helloFallback(){
        return "error";
    }
}
