package com.howtographql.hackernews.po;

import com.howtographql.hackernews.po.User;

/**
 * @author Ocean Liang
 * @date 2/26/2019
 */
public class SigninPayload {
    private final String token;
    private final User user;

    public SigninPayload(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }
}
