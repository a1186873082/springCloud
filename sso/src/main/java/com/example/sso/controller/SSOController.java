package com.example.sso.controller;

import com.example.sso.model.Result;
import com.example.sso.service.SSOService;
import com.example.sso.util.CookieUtil;
import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
public class SSOController {

    @Autowired
    private SSOService ssoService;

    /**
     * 单点登录校验,这里是单点登录认证中心
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/check_login", method = RequestMethod.GET)
    public Boolean checkLogin(HttpServletRequest request) throws Exception {
        Result result = new Result();
        HttpSession session = request.getSession();
        String token = session.getId();
        System.out.println("token:" + token);
        //校验cookie有效性
        return false;
    }

    /**
     * 简易的cookie跨域解决方案
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Result test(HttpServletRequest request, HttpServletResponse response) {
        String cookieName = "lcCookies";
        String cookieValue = CookieUtil.getCookieValue(cookieName, request);
        if(cookieValue == null){
            cookieValue = UUID.randomUUID().toString();
        }
        CookieUtil.setCookie(cookieName, cookieValue, request, response);
        Result result = new Result();
        result.setResultCode(1000);
        result.setResultData("ok");
        return result;
    }
}
