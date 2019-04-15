package com.example.sso.service;

import javax.servlet.http.Cookie;

public interface SSOService {

    public boolean checkLogin(Cookie[] cookies) throws Exception;
}
