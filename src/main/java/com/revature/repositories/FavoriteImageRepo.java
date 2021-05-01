package com.revature.repositories;

import com.revature.models.FavNasaImage;
import com.revature.models.NasaImage;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface FavoriteImageRepo extends CrudRepository<FavNasaImage,Integer> {

   // @Query(value = "SELECT * FROM Fav_Image WHERE url = :url", nativeQuery = true)
    List<FavNasaImage> findByUrl(final String url);

    @Modifying
    @Transactional
    @Query(value = "insert into fav_img_ref (user_id,img_id)\n" +
             " select u.id,img.id from users as u, fav_image as img where u.username =:username and  img.url = :url", nativeQuery = true)
    void updateFavoriteImageReferences(final String username, final String url);

    @Query(value = "select * from fav_image \n" +
            "join  Fav_Img_Ref as refs on id = refs.img_id\n" +
            "join Users as users on refs.user_id = users.id where users.username = :username",nativeQuery = true)
    List<NasaImage> getUserFavoriteImages(final String username);

}
