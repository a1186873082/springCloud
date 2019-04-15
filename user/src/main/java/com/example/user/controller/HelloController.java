package com.example.user.controller;

import com.example.user.DTO.Result;
import com.example.user.DTO.UserDTO;
import com.example.user.constant.UserConstant;
import com.example.user.mapper.model.User;
import com.example.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
public class HelloController {

    private Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() throws Exception {
        List<String> serviceIds = discoveryClient.getServices();
        if (!serviceIds.isEmpty()) {
            for (String s : serviceIds) {
                logger.info("serviceId" + s);

                List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances(s);
                if (!serviceInstanceList.isEmpty()) {
                    for (ServiceInstance serviceInstance : serviceInstanceList) {
                        int sleepLong = new Random().nextInt(2000);
                        logger.info("sleepLong:" + sleepLong);
                        Thread.sleep(sleepLong);
                        logger.info("/hello, host:" + serviceInstance.getHost() + ", service_id:" + serviceInstance.getServiceId());
                    }
                } else {
                    logger.info("no services");
                }
            }
        }

        return "HELLO World";
    }

    @RequestMapping(value = "/text_proxy", method = RequestMethod.GET)
    public String textProxy() throws Exception {
        return "success";
    }

    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    public String hello1(@ModelAttribute User user) throws Exception {
        return user.getUserName() + "*" + user.getFullName();
    }

    @RequestMapping(value = "/set_user", method = RequestMethod.POST)
    public String setUser(@RequestBody User user) {
        return user.getUserName() + "*" + user.getFullName();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody UserDTO userDTO) throws Exception {
        Result result = new Result();
        String resultMsg = userService.login(userDTO);
        result.setResultCode(UserConstant.SUCCESS);
        result.setResultMsg(resultMsg);
        return result;
    }
}
