package com.example.sso.model;

import com.auth0.jwt.interfaces.Claim;
import io.jsonwebtoken.Claims;

/**
 * 结果对象
 */
public class JWTResult {

    /**
     * 错误编码
     */
    private int errCode;

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 验证过程中payload中的数据
     */
    private Claims claim;

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Claims getClaim() {
        return claim;
    }

    public void setClaim(Claims claim) {
        this.claim = claim;
    }
}
