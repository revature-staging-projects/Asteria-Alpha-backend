package com.revature.repositories;

import com.revature.models.EPICImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EPICRepo extends CrudRepository<EPICImage,Integer> {


}
