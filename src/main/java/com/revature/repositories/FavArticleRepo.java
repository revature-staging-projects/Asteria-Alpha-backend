package com.revature.repositories;

import com.revature.models.FavEpicImage;
import com.revature.models.FavNews;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface FavArticleRepo extends CrudRepository<FavNews,Integer> {
//TODO change these values
    @Query(value = "select fav.id,fav.title,fav.image_date,fav.caption from Fav_Epic_Image as fav \n" +
            "join  Fav_Epic_Ref as refs on id = refs.img_id\n" +
            "join Users as users on refs.user_id = users.id where users.username = :username",nativeQuery = true)
    List<FavEpicImage> getUserFavoriteArticles(final String username);

    @Modifying
    @Transactional
    @Query(value = "insert into fav_Article_ref (user_id,img_id)\n" +
            " select u.id,img.id from users as u, fav_epic_image as img where u.username =:username and img.id = 1", nativeQuery = true)
    void updateRefTable(final String username);

}
