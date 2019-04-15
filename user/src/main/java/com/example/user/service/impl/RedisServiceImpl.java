package com.example.user.service.impl;

import com.example.user.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public void set(String key, Object object) throws Exception {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, object);
    }

    @Override
    public Object get(String key) throws Exception {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    @Override
    public void del(String key) throws Exception {
        if(existRedis(key)) {
            redisTemplate.delete(key);
        }
    }

    @Override
    public boolean existRedis(String key) throws Exception {
        boolean b = redisTemplate.hasKey(key);
        return b;
    }
}
