package com.example.user.mapper.model;

import java.io.Serializable;
import java.util.*;

/**
*
*  @author author
*/
public class User implements Serializable {

    private static final long serialVersionUID = 1555346950720L;


    /**
    * 主键
    * 主键
    * isNullAble:0
    */
    private Integer userId;

    /**
    * 登录账号
    * isNullAble:0,defaultVal:
    */
    private String userName;

    /**
    * 密码
    * isNullAble:0,defaultVal:
    */
    private String password;

    /**
    * 用户名
    * isNullAble:0,defaultVal:
    */
    private String fullName;

    /**
    * 
    * isNullAble:1
    */
    private Date recordTime;


    public void setUserId(Integer userId){this.userId = userId;}

    public Integer getUserId(){return this.userId;}

    public void setUserName(String userName){this.userName = userName;}

    public String getUserName(){return this.userName;}

    public void setPassword(String password){this.password = password;}

    public String getPassword(){return this.password;}

    public void setFullName(String fullName){this.fullName = fullName;}

    public String getFullName(){return this.fullName;}

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }
}
