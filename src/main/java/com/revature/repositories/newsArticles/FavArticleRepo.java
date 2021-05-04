package com.revature.repositories.newsArticles;

import com.revature.models.news.FavNews;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Repository to handle reading/writing data to/from database concerning favorited news articles.
 */
@Repository
public interface FavArticleRepo extends CrudRepository<FavNews,Integer> {
//TODO change these values
    @Query(value = "select fav.id,fav.title,fav.snippet,fav.url,fav.thumbnail_url from Fav_Article as fav \n" +
            "join Fav_Article_Ref as refs on fav.id = refs.article_id\n" +
            "join Users as users on refs.user_id = users.id where users.username = :username",nativeQuery = true)
    List<FavNews> getUserFavoriteArticles(final String username);

    @Modifying
    @Transactional
    @Query(value = "insert into Fav_Article_ref (user_id,article_id)\n" +
            " select u.id,art.id from users as u, Fav_Article as art where u.username =:username and art.url = :url", nativeQuery = true)
    void updateRefTable(final String username,final String url);

    @Modifying
    @Transactional
    @Query(value = "insert into Fav_Article (title,snippet, url,thumbnail_url)" +
            "select n.title,n.snippet,n.url,n.thumbnail_url from Article as n where n.url = :url",nativeQuery = true)
    void addImageToFav(final String url);


    List<FavNews> findByUrl(final String url);

}
