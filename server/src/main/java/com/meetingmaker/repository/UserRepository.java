package com.meetingmaker.repository;

import com.meetingmaker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    @Modifying
    @Query(value = "UPDATE users u set u.verify = :verify where u.id = :id", nativeQuery = true)
    @Transactional
    int updateVerify(@Param(value = "verify") boolean verify, @Param(value = "id") int id);

}
