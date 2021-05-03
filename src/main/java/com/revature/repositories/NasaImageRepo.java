package com.revature.repositories;

import com.revature.models.NasaImage;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface NasaImageRepo extends CrudRepository<NasaImage,Integer> {

    @Modifying
    @Transactional
    @Query(value = "ALTER SEQUENCE Image_id_seq RESTART WITH 1", nativeQuery = true)
    void resetCounter();


    @Modifying
    @Transactional
    @Query(value = "TRUNCATE Image", nativeQuery = true)
    void truncateDB();



}
