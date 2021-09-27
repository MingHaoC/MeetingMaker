package com.meetingmaker.service.impl;

import com.meetingmaker.util.ResponseFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HealthService {

    public ResponseEntity<String> healthCheck() {
        return ResponseFormatter.formatResponse("Server status: Online", HttpStatus.OK);
    }

}
