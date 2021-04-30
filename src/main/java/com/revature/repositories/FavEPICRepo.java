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
                "join  Fav_Epic_Ref as ref on id = ref.img_id\n" +
                "join Users as users on ref.user_id = users.id where users.id = :id",nativeQuery = true)
        List<FavEpicImage> getUserFavoriteImages(final Integer id);

        @Modifying
        @Transactional
        @Query(value = "insert into Fav_Epic_Ref (user_id,img_id) values (:user_id,:img_id)", nativeQuery = true)
        void updateRefTable(final Integer user_id, final Integer img_id);

        List<FavEpicImage> findByImage(final String title);
}
