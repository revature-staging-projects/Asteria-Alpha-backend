//package com.revature.repositories;
//
//import com.revature.models.NasaImage;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Random;
//
//@Repository
//public interface NasaImageRepo extends CrudRepository<NasaImage,Integer> {
//
//    public default NasaImage getRandomImage() {
//        final long count = count();
//        final int id = new Random().nextInt((int)count - 1) + 1;
//        return findById(id).orElseThrow(RuntimeException::new);
//    }
//
//    @Query(value = "ALTER SEQUENCE Nasa_Image_id_seq RESTART WITH 1;", nativeQuery = true)
//    public void resetCounter();
//
//    public void saveAll(List<NasaImage> collection);
//}
