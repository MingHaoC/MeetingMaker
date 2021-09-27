package com.meetingmaker.repository;

import com.meetingmaker.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
