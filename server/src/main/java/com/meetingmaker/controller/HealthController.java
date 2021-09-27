package com.meetingmaker.controller;

import com.datastax.oss.driver.api.core.CqlSession;
import com.meetingmaker.service.impl.HealthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HealthController {

    @Autowired
    HealthService healthService;

    @Autowired
    CqlSession session;

    @GetMapping("/health")
    public ResponseEntity<String> checkHealth(){
        return healthService.healthCheck();
    }

}
