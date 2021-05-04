package com.revature.repositories;

import com.revature.models.users.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<User,Integer> {


    @Query(value = "SELECT v.verified from Verified as v\n" +
            "JOIN Users as u on u.id = v.user_id\n" +
            "WHERE u.username = :username", nativeQuery = true)
    boolean getIfValid(final String username);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Verified (user_id)" +
                    "SELECT u.id from Users as u WHERE u.username = :username", nativeQuery = true)
    void addToVerifiedTable(final String username);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Verified SET verified = true WHERE user_id = :id",nativeQuery = true)
    void updateVerified(final int id);


    List<User> findByUsername(final String username);
}
