package com.revature.repositories;

import com.revature.models.NasaImage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteImageRepo extends CrudRepository<NasaImage,Integer> {

}
