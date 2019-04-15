package com.example.sso.service.impl;

import com.example.sso.service.SSOService;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

@Service
public class SSOServiceImpl implements SSOService {

    @Override
    public boolean checkLogin(Cookie[] cookies) throws Exception {
        for (Cookie cookie : cookies) {
            if("lichen".equals(cookie.getName())){
                return true;
            }
        }
        return false;
    }
}
