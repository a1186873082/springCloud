package com.example.sso.model;

import java.io.Serializable;

public class Result implements Serializable {
    private Integer resultCode;
    private String resultMsg;
    private Object resultData;
    private String token; //身份标识

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public Object getResultData() {
        return resultData;
    }

    public void setResultData(Object resultData)  {
        this.resultData = resultData;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
