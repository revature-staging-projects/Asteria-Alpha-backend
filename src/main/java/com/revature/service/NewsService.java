package com.revature.service;

import com.revature.repositories.NewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

    private final NewsRepo news_repo;

    @Autowired
    public NewsService(final NewsRepo news_repo) {
        this.news_repo = news_repo;
    }

}
