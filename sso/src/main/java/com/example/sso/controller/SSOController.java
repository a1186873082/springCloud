package com.example.sso.controller;

import com.example.sso.model.JWTResult;
import com.example.sso.model.JWTSubject;
import com.example.sso.model.JWTUser;
import com.example.sso.model.Result;
import com.example.sso.service.SSOService;
import com.example.sso.util.CookieUtil;
import com.example.sso.util.JWTUtil;
import io.jsonwebtoken.Claims;
import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
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
     * 校验token
     */
    @RequestMapping(value = "/sso_login",method = RequestMethod.GET)
    public Result ssoLogin(HttpServletRequest request) throws Exception{
        String token = request.getHeader("Authorization");
        JWTResult jwtResult = JWTUtil.validateJWT(token);
        Result result = new Result();
        if(jwtResult.isSuccess()){
            Claims claims = JWTUtil.parseJWT(token);
            String newtoken = JWTUtil.createJWT(claims.getId(), claims.getIssuer(), claims.getSubject(), 1*60*1000);
            result.setToken(newtoken);
            result.setResultMsg("成功");
            result.setResultData(null);
            result.setResultCode(200);
        }else{
            result.setToken(null);
            result.setResultMsg("失败");
            result.setResultData(null);
            result.setResultCode(jwtResult.getErrCode());
        }
        return result;

    }

    /**
     * JWT机制，实现token令牌的创建：JWT:一般用于用户身份验证和数据信息交换
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody HashMap<String, String> param)throws Exception{
        Result result = new Result();
        if(JWTUser.isLogin(param.get("userName"), param.get("password"))){
            JWTSubject jwtSubject = new JWTSubject(param.get("userName"));
            String token = JWTUtil.createJWT(UUID.randomUUID().toString(), "lcJWT", JWTUtil.generalSubject(jwtSubject), 1*60*1000);
            result.setResultCode(200);
            result.setResultData(null);
            result.setResultMsg("成功");
            result.setToken(token);
        }else{
            result.setResultCode(500);
            result.setResultData(null);
            result.setResultMsg("失败");
            result.setToken(null);
        }
        return result;
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
