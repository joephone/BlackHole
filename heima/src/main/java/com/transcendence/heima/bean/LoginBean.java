package com.transcendence.heima.bean;

/**
 * Login实体类
 *
 * @author gc
 * @since 1.0
 */
public class LoginBean {
    /**
     * userId : admin
     * token : test
     */
    private String userId;
    private String token;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "{" + "userId='" + userId + '\'' +
                ", token='" + token + '\'' + "}";
    }
}
