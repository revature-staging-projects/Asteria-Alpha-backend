package com.revature.repositories;

import com.revature.models.FavEpicImage;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface FavEPICRepo extends CrudRepository<FavEpicImage,Integer> {

        @Query(value = "select fav.id,fav.title,fav.image_date,fav.caption from Fav_Epic_Image as fav \n" +
                "join  Fav_Epic_Ref as refs on id = refs.img_id\n" +
                "join Users as users on refs.user_id = users.id where users.username = :username",nativeQuery = true)
        List<FavEpicImage> getUserFavoriteImages(final String username);

        @Modifying
        @Transactional
        @Query(value = "insert into fav_epic_ref (user_id,img_id)\n" +
                " select u.id,img.id from users as u, fav_epic_image as img where u.username =:username and img.image_date = :date", nativeQuery = true)
        void updateRefTable(final String username,final String date);



       @Modifying
       @Transactional
       @Query(value = "INSERT INTO Fav_Epic_Image (title,image_date,caption)" +
               "SELECT e.title,e.image_dat,e.caption from EPIC_Image where e.id = 1",nativeQuery = true)
        void addImageToFav();


        List<FavEpicImage> findByImage(final String title);
}
