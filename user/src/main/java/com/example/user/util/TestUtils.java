package com.example.user.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.HashMap;

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
            HttpUtils.sendGet("http://localhost:1001/test_redis", null);
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
        for (int i = 0; i < 10; i++) {
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
}
