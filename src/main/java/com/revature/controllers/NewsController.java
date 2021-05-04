package com.revature.controllers;

import com.revature.dto.users.PrincipalDTO;
import com.revature.models.news.FavNews;
import com.revature.models.news.NewsObject;
import com.revature.service.NewsService;
import com.revature.util.jwt.JwtParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Controller for handling request concerning news stories.
 */
@RestController
public class NewsController {

    final private NewsService news_service;
    final private JwtParser jwt_parser;

    @Autowired
    public NewsController(final NewsService news_service, final JwtParser jwt_parser) {
        this.news_service = news_service;
        this.jwt_parser       = jwt_parser;
    }


    /**
     * Method to retrieve every news story in teh database.
     * @return
     */
    @GetMapping(path = "/news",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)
    public List<NewsObject> getAllNews() {
        return news_service.getAllNews();
    }


    /**
     * Method to add a news story to a user's favorites.
     * @param url url of the news story.
     * @param request holds JWT which is used to identify the user.
     */
    @PutMapping(path = "/addfavoritearticle")
    @ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)
    public void addArticleToFavorites(@RequestParam(name = "url") final String url, final HttpServletRequest request) {
        final String token      = jwt_parser.getTokenFromHeader(request);
        final PrincipalDTO user = jwt_parser.parseToken(token);
        news_service.addArticleToFavorites(url,user.getUsername());

    }

    /**
     * Method which is used to retrieve every article favorited by a user.
     * @param request holds JWT which is used to identify the user.
     * @return List of every article favorited by a user.
     */
    @GetMapping(path = "/getnewsfavorites")
    @ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)
    public List<FavNews> getUserFavoriteArticles(final HttpServletRequest request) {
        final String token      = jwt_parser.getTokenFromHeader(request);
        final PrincipalDTO user = jwt_parser.parseToken(token);
        return news_service.getUserFavorites(user.getUsername());
    }

}
