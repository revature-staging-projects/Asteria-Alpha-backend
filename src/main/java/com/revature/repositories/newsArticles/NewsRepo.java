package com.revature.repositories.newsArticles;

import com.revature.models.news.NewsObject;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Repository to handle reading/writing to/from database concerning news articles.
 */
@Repository
public interface NewsRepo extends CrudRepository<NewsObject,Integer> {

    @Modifying
    @Transactional
    @Query(value = "ALTER SEQUENCE Article_id_seq RESTART WITH 1", nativeQuery = true)
    void resetCounter();


    @Modifying
    @Transactional
    @Query(value = "TRUNCATE Article", nativeQuery = true)
    void truncateDB();

}
