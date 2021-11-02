package com.meetingmaker.repository;

import com.meetingmaker.entity.EmailVerify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface EmailVerifyRepo extends JpaRepository<EmailVerify, String> {

    @Modifying
    @Query(value = "insert into email_verify (uuid,user_id) VALUES (:uuid,:user_id)", nativeQuery = true)
    @Transactional
    int save(@Param("uuid") String uuid, @Param("user_id") int userId);

    Optional<EmailVerify> findByUuid(String id);

}