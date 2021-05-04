package com.revature.controllers;

import com.revature.dto.PrincipalDTO;
import com.revature.models.NewsObject;
import com.revature.service.NewsService;
import com.revature.util.jwt.JwtParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class NewsController {

    final private NewsService news_service;
    final private JwtParser jwt_parser;

    @Autowired
    public NewsController(final NewsService news_service, final JwtParser jwt_parser) {
        this.news_service = news_service;
        this.jwt_parser       = jwt_parser;
    }


    @GetMapping(path = "/news",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)
    public List<NewsObject> getAllNews() {
        return news_service.getAllNews();
    }


    @PutMapping(path = "/addfavoritearticle")
    @ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)
    public void addArticleToFavorites(@RequestParam(name = "url") final String url, final HttpServletRequest request) {
        final String token      = jwt_parser.getTokenFromHeader(request);
        final PrincipalDTO user = jwt_parser.parseToken(token);
        news_service.addArticleToFavorites(url,user.getUsername());

    }


}
