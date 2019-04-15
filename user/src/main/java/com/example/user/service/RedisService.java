package com.example.user.service;

public interface RedisService {

    void set(String key, Object object) throws Exception;

    Object get(String key) throws Exception;

    void del(String key) throws Exception;

    boolean existRedis(String key) throws Exception;

}
