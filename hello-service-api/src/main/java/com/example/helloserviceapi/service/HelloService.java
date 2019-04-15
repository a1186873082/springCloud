package com.example.helloserviceapi.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/refactor")
public interface HelloService {

    @RequestMapping(value = "hello4", method = RequestMethod.GET)
    String hello(@RequestParam("name") String name);

}
