package com.revature.repositories.epic;

import com.revature.models.epicImages.EPICImage;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Repository to handle writing and retrieving EPIC image info to/from the database.
 */
@Repository
public interface EPICRepo extends CrudRepository<EPICImage,Integer> {

    @Modifying
    @Transactional
    @Query(value = "ALTER SEQUENCE EPIC_Image_id_seq RESTART WITH 1", nativeQuery = true)
    void resetCounter();

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE EPIC_Image", nativeQuery = true)
    void truncateDB();

}
