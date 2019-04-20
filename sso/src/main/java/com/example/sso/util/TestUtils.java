package com.example.sso.util;

import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;

public class TestUtils implements Runnable {


    /**
     * @Author: yinqianhui
     * @Date Created by ThinkPad on 2017/8/24.
     */

    @Autowired
    RedisTemplate redisTemplate;

    public TestUtils() {
        redisTemplate = new RedisTemplate();
    }

    public void run() {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("name", Thread.currentThread().getName());
            HttpUtils.sendGet("http://localhost:1001/test_redis", params);
        } catch (Exception e) {
            System.out.println("错误：" + e);
        }
    }

    public static void main(String[] args) {
        //为每隔线程获取一个jedis，这是没问题的
   /*     for(int i=0;i<10;i++) {
            Jedis jedis1 = RedisUtil.getJedis();
            MutiTest mutiTest1 = new MutiTest(jedis1);
            Thread t1 = new Thread(mutiTest1);
            t1.start();
            Jedis jedis2 = RedisUtil.getJedis();
            MutiTest mutiTest2 = new MutiTest(jedis2);
            Thread t2 = new Thread(mutiTest2);
            t2.start();
        }*/
        System.out.println("----------------猥琐的分割线--------------------");
        //所有线程共用一个jedis，会出现错误
            TestUtils mutiTest1 = new TestUtils();
            Thread t1 = new Thread(mutiTest1);
            t1.start();
            TestUtils mutiTest2 = new TestUtils();
            Thread t2 = new Thread(mutiTest2);
            t2.start();
            TestUtils mutiTest3 = new TestUtils();
            Thread t3 = new Thread(mutiTest3);
            t3.start();
    }
}
