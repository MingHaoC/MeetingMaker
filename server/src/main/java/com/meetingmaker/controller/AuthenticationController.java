package com.meetingmaker.controller;


import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.meetingmaker.entity.User;
import com.meetingmaker.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.InsertOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    CqlSession session;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        return authenticationService.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody User user) {
        return authenticationService.login(user.getEmail(), user.getPassword());
    }


}
