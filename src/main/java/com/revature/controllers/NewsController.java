package com.revature.controllers;

import com.revature.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsController {

    final private NewsService news_service;

    @Autowired
    public NewsController(final NewsService news_service) {
        this.news_service = news_service;
    }


}
