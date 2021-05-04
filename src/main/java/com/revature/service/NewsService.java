package com.revature.service;

import com.revature.dto.NewsDTO;
import com.revature.models.news.FavNews;
import com.revature.models.news.NewsObject;
import com.revature.repositories.FavArticleRepo;
import com.revature.repositories.NewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
public class NewsService {

    private final NewsRepo news_repo;
    private final FavArticleRepo fav_repo;
    private final List<String> search_terms = Collections.unmodifiableList(Arrays.asList("Nasa","Astronomy"));

    @Autowired
    public NewsService(final NewsRepo news_repo, final FavArticleRepo fav_repo) {
        this.news_repo = news_repo;
        this.fav_repo  = fav_repo;
    }



    private NewsObject[] getNewsArticles(final String search_term) {
        final String url ="https://contextualwebsearch-websearch-v1.p.rapidapi.com/api/search/NewsSearchAPI";
        return WebClient.create(url).get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("q",search_term)
                        .queryParam("pageNumber","1")
                        .queryParam("pageSize","15")
                        .queryParam("autoCorrect","true")
                        .queryParam("withThumbnails","true")
                        .queryParam("fromPublishedDate","null")
                        .queryParam("toPublishedDate","null")
                        .queryParam("safeSearch","true")
                        .build())
                .header("x-rapidapi-key",System.getenv("news_api"))
                .header("x-rapidapi-host","contextualwebsearch-websearch-v1.p.rapidapi.com")
                .header("useQueryString","true")
                .retrieve()
                .bodyToMono(NewsDTO.class)
                .blockOptional().orElseThrow(RuntimeException::new).getValue();
    }

    @Scheduled(fixedRate = 86400000)
    public void addArticlesToDB() {
        news_repo.truncateDB();
        news_repo.resetCounter();
        final List<NewsObject> news = new LinkedList<>();
        for(int i = 0; i < 2; i++) {
            news.addAll(Arrays.asList(getNewsArticles(search_terms.get(i))));
        }
        Collections.shuffle(news);
        news_repo.saveAll(news);
    }

    public List<NewsObject> getAllNews() {
        final List<NewsObject> news = new ArrayList<>();
        news_repo.findAll().forEach(news::add);
        return news;
    }


    private boolean checkIfExists(final String url) {
        final List<FavNews> news = fav_repo.findByUrl(url);
        return news != null && news.size() > 0;
    }

    public void addArticleToFavorites(final String url, final String username) {
        if(!checkIfExists(url)) {
            fav_repo.addImageToFav(url);
        }
        fav_repo.updateRefTable(username,url);
    }

    public List<FavNews> getUserFavorites(final String username) {
        return fav_repo.getUserFavoriteArticles(username);
    }

}
