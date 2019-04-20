package com.example.user.service.impl;

import com.example.user.DTO.UserDTO;
import com.example.user.mapper.UserMapper;
import com.example.user.mapper.model.User;
import com.example.user.service.RedisService;
import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public String login(UserDTO userDTO) throws Exception {
        if (userDTO.getUserName() == null) {
            return "用户名不能为空";
        }
        if (userDTO.getPassword() == null) {
            return "密码不能为空";
        }
        //判断用户名与密码是否正确
        //根据用户名从数据库中找到对应的数据
        User user = userMapper.getUser(userDTO.getUserName());
        //判断密码是否正确
        if (user != null) {
            if (userDTO.getPassword().equals(user.getPassword())) {
                //登录成功后将信息放入redis中
                addRedis(user.getUserId());
                //TODO 是否存在生成token令牌逻辑，还是说放到sso
                return "success";
            } else {
                return "密码输入有误";
            }
        } else {
            return "用户名错误,请确认后重新输入";
        }
    }

    //实现redis具体方法


    @Override
    public void addRedis(Integer userId) throws Exception {
        //根据userId得到user信息
        User user = userMapper.getUserById(userId);
        if (redisService.existRedis("user")) {
            Map<Integer, User> userMap = (Map<Integer, User>) redisService.get("user");
            if(!userMap.containsKey(userId)){
                userMap.put(user.getUserId(), user);
            }
            redisService.set("user", userMap);
        } else {
            Map<Integer, User> userMap = new HashMap<>();
            userMap.put(user.getUserId(), user);
            redisService.set("user", userMap);
        }
    }

    @Override
    public void testRedis(String params) throws Exception {
        redisService.set(params, params);
        while(true){
            System.out.println(redisService.get(params));
        }
    }
}
