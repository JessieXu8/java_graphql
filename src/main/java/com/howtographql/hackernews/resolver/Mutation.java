package com.howtographql.hackernews.resolver;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import com.howtographql.hackernews.context.AuthContext;
import com.howtographql.hackernews.po.AuthData;
import com.howtographql.hackernews.po.SigninPayload;
import com.howtographql.hackernews.po.Link;
import com.howtographql.hackernews.po.User;
import com.howtographql.hackernews.repository.LinkRepository;
import com.howtographql.hackernews.repository.UserRepository;
import graphql.GraphQLException;
import graphql.schema.DataFetchingEnvironment;

/**
 * @author Ocean Liang
 * @date 2/25/2019
 */
public class Mutation implements GraphQLRootResolver {
    private final LinkRepository linkRepository;
    private final UserRepository userRepository;

    public Mutation(LinkRepository linkRepository, UserRepository userRepository) {
        this.linkRepository = linkRepository;
        this.userRepository = userRepository;
    }

    public Link createLink(String url, String description , DataFetchingEnvironment environment) {
        AuthContext context = environment.getContext();
        Link newLink = new Link(url, description,context.getUser().getId());
        linkRepository.saveLink(newLink);
        return newLink;
    }

    public User createUser(String name, AuthData authData) {
        User user = new User(name, authData.getEmail(), authData.getPassword());
        return userRepository.saveUser(user);
    }

    public SigninPayload signinUser(AuthData auth) throws IllegalAccessException {
        User user = userRepository.findByEmail(auth.getEmail());
        if (user.getPassword().equals(auth.getPassword())) {
            return new SigninPayload(user.getId(), user);
        }
        throw new GraphQLException("Invalid credentials");
    }
}
