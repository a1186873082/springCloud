package com.example.user.service;

public interface RedisService {

    void set(String key, Object object) throws Exception;

    Object get(String key) throws Exception;

    void del(String key) throws Exception;

    boolean existRedis(String key) throws Exception;

//    boolean lock(String key, long expire);
//
//    boolean unLock(String key);

    boolean lock(String key, String value);

    void unLock(String key, String value);
}
