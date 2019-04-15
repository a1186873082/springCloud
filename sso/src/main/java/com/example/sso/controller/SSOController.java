package com.example.sso.controller;

import com.example.sso.model.Result;
import com.example.sso.service.SSOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
public class SSOController {

    @Autowired
    private SSOService ssoService;

    /**
     * 单点登录校验,这里是单点登录认证中心
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/check_login", method = RequestMethod.GET)
    public Boolean checkLogin(HttpServletRequest request) throws Exception{
        Result result = new Result();
        //校验cookie有效性
        return false;
    }
}
