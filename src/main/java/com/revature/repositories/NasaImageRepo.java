package com.revature.repositories;

import com.revature.models.NasaImage;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface NasaImageRepo extends CrudRepository<NasaImage,Integer> {

    @Modifying
    @Transactional
    @Query(value = "ALTER SEQUENCE Image_id_seq RESTART WITH 1", nativeQuery = true)
    void resetCounter();

    @Query(value = "SELECT * FROM Fav_Image WHERE url = :url", nativeQuery = true)
    List<NasaImage> getFavoriteImageByUrl(final String url);

    @Query(value = "insert into Fav_Img_Ref (user_id,img_id) values (:user_id,:img_id)", nativeQuery = true)
    void updateFavoriteImageReferences(final Integer user_id, final Integer img_id);

    @Query(value = "INSERT INTO Fav_Image (url,title,description) values (?,?,?)", nativeQuery = true)
    void saveImageToFavoriteImages(final NasaImage img);

    @Query(value = "select * from fav_image \n" +
            "join  Fav_Img_Ref as ref on id = ref.img_id\n" +
            "join Users as users on ref.user_id = users.id where users.id = :id",nativeQuery = true)
    List<NasaImage> getUserFavoriteImages(final Integer id);

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE Image", nativeQuery = true)
    void truncateDB();

}
