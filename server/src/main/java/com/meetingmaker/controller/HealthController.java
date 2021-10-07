package com.meetingmaker.controller;

import com.meetingmaker.service.impl.HealthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.Time;

import static com.meetingmaker.constant.Route.*;

@RestController
@RequestMapping(ADMIN)
public class HealthController {

    @Autowired
    HealthService healthService;

    @GetMapping(HEALTH)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> checkHealth() {
        return healthService.healthCheck();
    }

    @GetMapping(TEST)
    @Secured("ROLE_ADMIN")
    public Time test() {
        Time time = new Time(System.currentTimeMillis());
        System.out.println(time);
        return time;
    }
}
