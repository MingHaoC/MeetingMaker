package com.meetingmaker.controller;

import com.meetingmaker.service.impl.HealthService;

import com.meetingmaker.util.Mailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

import static com.meetingmaker.constant.Route.*;

@RestController
@RequestMapping(ADMIN)
public class HealthController {

    @Autowired
    HealthService healthService;

    @Autowired
    Mailer mailer;

    @GetMapping(HEALTH)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> checkHealth() {
        return healthService.healthCheck();
    }

    @GetMapping(TEST)
    @Secured("ROLE_ADMIN")
    public void test() throws IOException {
        String emailVerification = mailer.readTemplateFile("/EmailVerification.html");
        emailVerification = emailVerification.replaceAll("%activationLink%", "http://localhost:3000");
        mailer.sendEmail(emailVerification, "Email Verification", "minghao2@outlook.com");
        System.out.println(emailVerification);
    }
}
