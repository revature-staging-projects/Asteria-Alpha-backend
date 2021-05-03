package com.revature.repositories;

import com.revature.models.NewsObject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepo extends CrudRepository<NewsObject,Integer> {
}
