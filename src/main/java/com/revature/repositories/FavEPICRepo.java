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

        @Query(value = "select * from Fav_Epic_Image \n" +
                "join  Fav_Epic_Ref as refs on id = refs.img_id\n" +
                "join Users as users on refs.user_id = users.id where users.username = :username",nativeQuery = true)
        List<FavEpicImage> getUserFavoriteImages(final String username);

        @Modifying
        @Transactional
        @Query(value = "insert into fav_epic_ref (user_id,img_id)\n" +
                " select u.id,img.id from users as u, fav_epic_image as img where u.username =:username and img.image_date = :date", nativeQuery = true)
        void updateRefTable(final String username,final String date);

        List<FavEpicImage> findByImage(final String title);
}
