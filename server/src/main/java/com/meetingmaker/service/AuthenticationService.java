package com.meetingmaker.service;

import com.meetingmaker.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface AuthenticationService {

    ResponseEntity<String> register(User user);

    ResponseEntity<String> login(String username, String password);

    ResponseEntity<String> emailVerify(String uuid);
}
