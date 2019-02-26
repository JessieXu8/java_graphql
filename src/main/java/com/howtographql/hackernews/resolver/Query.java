package com.howtographql.hackernews.resolver;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import com.howtographql.hackernews.po.Link;
import com.howtographql.hackernews.repository.LinkRepository;

import java.util.List;

/**
 * Query root. Contains top-level queries.
 */
public class Query implements GraphQLRootResolver {

    private final LinkRepository linkRepository;

    public Query(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public List<Link> allLinks() {
        return linkRepository.getAllLinks();
    }
}
