package com.thedrinkwholesale.dws.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    Optional<User> findByuserid(String userid);

    @Query(value = "select u.userid,u.password from User u where u.userid = ?1")
    Optional<Object> findByUseridAndPassword(String userid);


}
