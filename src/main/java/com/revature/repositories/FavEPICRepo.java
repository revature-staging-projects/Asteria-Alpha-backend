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
        @Query(value = "insert into Fav_Epic_Ref (user_id,img_id) values (:user_id,:img_id)", nativeQuery = true)
        void updateRefTable(final Integer user_id, final Integer img_id);

        List<FavEpicImage> findByImage(final String title);
}
