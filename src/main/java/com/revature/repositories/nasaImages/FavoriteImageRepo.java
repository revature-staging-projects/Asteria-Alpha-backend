package com.revature.repositories.nasaImages;

import com.revature.models.nasaImages.FavNasaImage;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Repository to handle writing and retrieving data to.from the database concerning NASa images which have been favorited.
 */
@Repository
public interface FavoriteImageRepo extends CrudRepository<FavNasaImage,Integer> {

   // @Query(value = "SELECT * FROM Fav_Image WHERE url = :url", nativeQuery = true)
    List<FavNasaImage> findByUrl(final String url);

    @Modifying
    @Transactional
    @Query(value = "insert into fav_img_ref (user_id,img_id)\n" +
             " select u.id,img.id from users as u, fav_image as img where u.username =:username and img.url = :url", nativeQuery = true)
    void updateFavoriteImageReferences(final String username, final String url);

    @Query(value = "select fav.id,fav.url,fav.title,fav.description from Fav_Image as fav \n" +
            "join Fav_Img_Ref as refs on fav.id = refs.img_id\n" +
            "join Users as users on refs.user_id = users.id where users.username = :username",nativeQuery = true)
    List<FavNasaImage> getUserFavoriteImages(final String username);

    @Modifying
    @Transactional
    @Query(value = "insert into Fav_Image (url,title,description)" +
         "select n.url,n.title,n.description from Image as n where n.url = :url",nativeQuery = true)
   void addImageToFav(final String url);
}
