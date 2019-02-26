package com.howtographql.hackernews;


import com.coxautodev.graphql.tools.SchemaParser;
import com.howtographql.hackernews.context.AuthContext;
import com.howtographql.hackernews.po.User;
import com.howtographql.hackernews.repository.LinkRepository;
import com.howtographql.hackernews.repository.UserRepository;
import com.howtographql.hackernews.resolver.LinkResolver;
import com.howtographql.hackernews.resolver.Mutation;
import com.howtographql.hackernews.resolver.Query;
import com.howtographql.hackernews.resolver.SigninResolver;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import graphql.schema.GraphQLSchema;
import graphql.servlet.GraphQLContext;
import graphql.servlet.SimpleGraphQLServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * The servlet acting as the GraphQL endpoint
 */
@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {

    private static final LinkRepository linkRepository;
    private static final UserRepository userRepository;

    static {
        MongoDatabase mongo = new MongoClient().getDatabase("hackernews");
        linkRepository = new LinkRepository(mongo.getCollection("links"));
        userRepository = new UserRepository(mongo.getCollection("users"));
    }

    public GraphQLEndpoint() {
        super(buildSchema());
    }

    private static GraphQLSchema buildSchema() {
        return SchemaParser.newParser()
                .file("schema.graphqls")
                .resolvers( //注册新的解析器
                        new Query(linkRepository),
                        new Mutation(linkRepository, userRepository),
//                        new SigninResolver(),
                        new LinkResolver(userRepository))
                .build()
                .makeExecutableSchema();
    }

    @Override
    protected GraphQLContext createContext(Optional<HttpServletRequest> request, Optional<HttpServletResponse> response) {
        User user = userRepository.findByEmail("test email");
        return new AuthContext(user, request, response);
    }
}
