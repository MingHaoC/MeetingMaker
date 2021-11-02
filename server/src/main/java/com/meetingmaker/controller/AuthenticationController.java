package com.meetingmaker.controller;

import com.meetingmaker.entity.Input.EmailVerifyIn;
import com.meetingmaker.entity.User;
import com.meetingmaker.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.meetingmaker.constant.Route.*;

@RestController
@RequestMapping(USER)
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(REGISTER)
    public ResponseEntity<String> register(@RequestBody User user) {
        return authenticationService.register(user);
    }

    @PostMapping(LOGIN)
    public ResponseEntity<String> login(@RequestBody User user) {
        return authenticationService.login(user.getEmail(), user.getPassword());
    }

    @PostMapping(EMAIL_VERIFY)
    public ResponseEntity<String> emailVerify(@RequestBody EmailVerifyIn data) {
        System.out.println(data.getUuid());
        return authenticationService.emailVerify(data.getUuid());
    }


}
