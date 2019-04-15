package com.example.user.service;

import com.example.user.DTO.UserDTO;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {

    String login(@RequestBody UserDTO userDTO) throws Exception;

    //将user信息存入缓存
    void addRedis(Integer userId) throws Exception;
}
