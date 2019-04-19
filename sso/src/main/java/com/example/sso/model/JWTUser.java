package com.example.sso.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户的验证，模拟的数据库
 */
public class JWTUser {
    private static final Map<String, String> USERS = new HashMap<>();

    /**
     * 静态代码块，初始化USERS对象
     */
    static {
        for (int i = 0; i < 10; i++) {
            USERS.put("admin" + i, "password" + i);
        }
    }

    //验证用户是否登录
    public static boolean isLogin(String userName, String password) {
        if (null == userName || userName.trim().length() == 0) {
            return false;
        }
        String obj = USERS.get(userName);
        if (null == obj || !obj.equals(password)) {
            return false;
        }
        return true;
    }

}
