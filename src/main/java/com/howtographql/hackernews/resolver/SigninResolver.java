package com.howtographql.hackernews.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.howtographql.hackernews.po.SigninPayload;
import com.howtographql.hackernews.po.User;

/**
 * @author Ocean Liang
 * @date 2/26/2019
 */
public class SigninResolver implements GraphQLResolver<SigninPayload> {

    public User user(SigninPayload payload) {
        return payload.getUser();
    }
}
