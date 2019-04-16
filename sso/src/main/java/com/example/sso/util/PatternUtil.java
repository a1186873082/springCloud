package com.example.sso.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtil {

    public static String getDomain(String url){
        Pattern pattern = Pattern.compile("[^//]*?\\.(com|cn|new|org|biz|info|cc|ttv)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(url);
        matcher.find();
        return matcher.group();
    }

    public static void main(String[] args) {
        String s = "http://www.test.com:1006/order";
        String domain = getDomain(s);
        System.out.println(domain);
    }
}
