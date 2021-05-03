package com.revature.repositories;

import com.revature.models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<User,Integer> {


    @Query(value = "select v.verified from Verified as v\n" +
            "join Users as u on u.id = v.user_id\n" +
            "where u.username = :username", nativeQuery = true)
    boolean getIfValid(final String username);

    @Transactional
    @Modifying
    @Query(value = "insert into verified (user_id)" +
                    "Select u.id from users as u where u.username = :username", nativeQuery = true)
    void addToVerifiedTable(final String username);


    List<User> findByUsername(final String username);
}
