package com.meetingmaker.service;

import com.meetingmaker.entity.User;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {

    ResponseEntity<String> register(User user);

    ResponseEntity<String> login(String username, String password);

}
