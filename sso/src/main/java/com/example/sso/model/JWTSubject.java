package com.example.sso.model;

/**
 * 作为subject数据使用，也就是payload中保存的public claims
 */
public class JWTSubject {
    private String userName;

    public JWTSubject(){
        super();
    }

    public JWTSubject(String userName){
        super();
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
