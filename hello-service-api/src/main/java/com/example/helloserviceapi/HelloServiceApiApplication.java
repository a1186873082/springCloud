package com.example.helloserviceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class HelloServiceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloServiceApiApplication.class, args);
    }

}
