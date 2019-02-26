package com.howtographql.hackernews.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.howtographql.hackernews.po.Link;
import com.howtographql.hackernews.po.User;
import com.howtographql.hackernews.repository.UserRepository;

/**
 * @author Ocean Liang
 * @date 2/26/2019
 */
public class LinkResolver implements GraphQLResolver<Link> {
    private UserRepository userRepository;

    public LinkResolver(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User postedBy(Link link) {
        if (link.getUserId() == null) {
            return null;
        }
        return userRepository.findById(link.getUserId());
    }
}
