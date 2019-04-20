package com.example.sso.util;

import com.alibaba.fastjson.JSONObject;
import com.example.sso.model.JWTResult;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * JWT工具
 */
public class JWTUtil {
    //服务器的key。用于做加解密的key数据
    private static final String JWT_SECERT = "test_jwt_secert";

    private static final int JWT_ERRCODE_EXPIRE = 1005; //Token过期
    private static final int JWT_ERRCODE_FAIL = 1006; //验证不通过

    /**
     * 获取服务器的key（密匙）
     *
     * @return
     */
    public static SecretKey generalKey() {
        try {
            byte[] encodeKey = JWT_SECERT.getBytes("UTF-8");
            SecretKey key = new SecretKeySpec(encodeKey, 0, encodeKey.length, "AES");
            return key;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 签发JWT，创建tokan的方法
     *
     * @param id        jwt的唯一身份标识，主要用来作为一次性token
     * @param iss       jwt的签发者
     * @param subject   jwt所面向的用户。payload中记录的public claims，，我这里面向的是用户名
     * @param ttlMillis 有效期，单位毫秒
     * @return token token是一次性的。是为一个用户的有效登录周期准备的一个token。用户退出登录或超市，token失效
     */
    public static String createJWT(String id, String iss, String subject, long ttlMillis) {
        //加密算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //当前时间
        long nowMillis = System.currentTimeMillis();
        //当前时间的日期对象
        Date now = new Date(nowMillis);
        //获取key
        SecretKey secretKey = generalKey();
        //创建JWT的构建器，就是使用指定的信息和加密算法，生成token的工具
        JwtBuilder builder = Jwts.builder()
                .setId(id) //设置身份标志(一个客户端的唯一标记，可以使用用户主键，客户端ip，或随机生成数据)
                .setIssuer(iss) //签发者
                .setSubject(subject)
                .setIssuedAt(now) //签发时间,token生成时间
                .signWith(signatureAlgorithm, secretKey); //设定密匙和算法
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date expDate = new Date(expMillis);
            builder.setExpiration(expDate);
        }
        return builder.compact(); //生成token

    }

    /**
     * 验证JWT
     */
    public static JWTResult validateJWT(String jwtStr) {
        JWTResult jwtResult = new JWTResult();
        Claims claims = null;
        try {
            //todo 解析jwt
            claims = parseJWT(jwtStr);
            jwtResult.setSuccess(true);
            jwtResult.setClaim(claims);
        } catch (ExpiredJwtException e) {
            jwtResult.setSuccess(false);
            jwtResult.setErrCode(JWT_ERRCODE_EXPIRE);
        } catch (SignatureException e) {
            jwtResult.setSuccess(false);
            jwtResult.setErrCode(JWT_ERRCODE_FAIL);
        } catch (Exception e) {
            jwtResult.setSuccess(false);
            jwtResult.setErrCode(JWT_ERRCODE_FAIL);
        }
        return jwtResult;
    }

    /**
     * 解析JWT字符串
     *
     * @param jwt 就是服务器为客户端生成的签名数据，就是token
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody(); //获取的是token中记录的payload数据，就是payload中保存的所有claims
    }

    /**
     * 转subject对象变为json字符串
     * @param subObj
     * @return
     */
    public static String generalSubject(Object subObj) {
        try {
            return JSONObject.toJSONString(subObj);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
