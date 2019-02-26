package com.howtographql.hackernews.context;

import com.howtographql.hackernews.po.User;
import graphql.servlet.GraphQLContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * @author Ocean Liang
 * @date 2/26/2019
 */
public class AuthContext extends GraphQLContext {
    private final User user;

    public AuthContext(User user, Optional<HttpServletRequest> request, Optional<HttpServletResponse> response) {
        super(request, response);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
