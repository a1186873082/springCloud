package com.example.user.service.impl;

import com.example.user.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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
        if (existRedis(key)) {
            redisTemplate.delete(key);
        }
    }

    @Override
    public boolean existRedis(String key) throws Exception {
        boolean b = redisTemplate.hasKey(key);
        return b;
    }

    @Override
    public boolean lock(String key, String value) {
        if (this.redisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        } else {
            String currValue = (String) this.redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currValue) && Long.parseLong(currValue) < System.currentTimeMillis()) {
                String oldValue = (String) this.redisTemplate.opsForValue().getAndSet(key, value);
                if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currValue)) {
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public void unLock(String key, String value) {
        try {
            String currValue = (String) this.redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currValue) && currValue.equals(value)) {
                this.redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception var4) {
            System.out.println("解锁异常");
            var4.printStackTrace();
        }

    }

    public static void main(String[] args) {

    }

//    //获取分布式锁
//    @Override
//    private boolean lock(String key, long expire) {
//        try {
//            Object result = redisTemplate.execute(new RedisCallback<String>() {
//                @Override
//                public String doInRedis(RedisConnection connection) throws DataAccessException {
//                    JedisCommands commands = (JedisCommands) connection.getNativeConnection();
//                    String uuid = UUID.randomUUID().toString();
//                    lockFlag.set(uuid);
//                    return commands.set(key, uuid, "NX", "PX", expire);
//                }
//            });
//            return !StringUtils.isEmpty(result);
//        } catch (Exception e) {
//        }
//        return false;
//    }
//
//    //释放分布式锁
//    @Override
//    public boolean unLock(String key) {
//        //   释放锁的时候，有可能因为持锁之后方法执行时间大于锁的有效期，此时有可能已经被另外一个线程持有锁，所以不能直接删除
//        try {
//            List<String> keys = new ArrayList<String>();
//            keys.add(key);
//            List<String> args = new ArrayList<String>();
//            args.add(lockFlag.get());
//            //   使用lua脚本删除redis中匹配value的key，可以避免由于方法执行时间过长而redis锁自动过期失效的时候误删其他线程的锁
//            //   spring自带的执行脚本方法中，集群模式直接抛出不支持执行脚本的异常，所以只能拿到原redis的connection来执行脚本
//            Long result = (Long) redisTemplate.execute(new RedisCallback<Long>() {
//                public Long doInRedis(RedisConnection connection) throws DataAccessException {
//                    Object nativeConnection = connection.getNativeConnection();
//                    //   集群模式和单机模式虽然执行脚本的方法一样，但是没有共同的接口，所以只能分开执行
//                    //   集群模式
//                    if (nativeConnection instanceof JedisCluster) {
//                        return (Long) ((JedisCluster) nativeConnection).eval(UNLOCK_LUA, keys, args);
//                    }
//                    //   单机模式
//                    else if (nativeConnection instanceof Jedis) {
//                        return (Long) ((Jedis) nativeConnection).eval(UNLOCK_LUA, keys, args);
//                    }
//                    return 0L;
//                }
//            });
//            return result != null && result > 0;
//        } catch (Exception e) {
//        }
//        return false;
//    }
}
