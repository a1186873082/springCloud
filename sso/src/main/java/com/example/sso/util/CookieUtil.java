package com.example.sso.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    //获取CookieValue
    public static String getCookieValue(String cookieName, HttpServletRequest request){
        String returnValue = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    returnValue = cookie.getValue();
                }
            }
        }
        return returnValue;
    }

    //设置Cookie
    public static  void setCookie(String cookieName, String cookieValue, HttpServletRequest request, HttpServletResponse response){
        String url = request.getRequestURL().toString();
        String domain = PatternUtil.getDomain(url);
        String[] domains = domain.split("\\.");
        int len = domains.length;
        if(len>3){
            domain = domains[len-3]+"."+domains[len-2]+"."+domains[len-1];
        }else if(len<=3&&len>1){
            domain = domains[len-2]+"."+domains[len-1];
        }else{

        }

        Cookie cookie = new Cookie(cookieName, cookieValue);
        if(!"localhost".equals(domain)){
            cookie.setDomain(domain);
        }
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
