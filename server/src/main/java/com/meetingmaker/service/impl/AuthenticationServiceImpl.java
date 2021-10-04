package com.meetingmaker.service.impl;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.meetingmaker.component.JwtTokenProvider;
import com.meetingmaker.entity.User;
import com.meetingmaker.repository.UserRepository;
import com.meetingmaker.service.AuthenticationService;
import com.meetingmaker.util.sendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.InsertOptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    CqlSession cqlSession;

    @Autowired
    sendMail sm;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Override
    public ResponseEntity<String> register(User user) {
        // crud repo doesn't support cassandra lightweight transaction, so I used CassandraOperation to insert
        try {
            CassandraOperations co = new CassandraTemplate(cqlSession);
            User newUser = new User(Uuids.random().toString(), user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName(), new Date(System.currentTimeMillis()));
            newUser.hashPassword();
            if (!co.insert(newUser, InsertOptions.builder().ifNotExists(true).build()).wasApplied())
                return new ResponseEntity<>("An account with that email already exists", HttpStatus.CONFLICT);
            // TODO: send out email to verify user email
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Something went wrong in the AuthenticationServiceImpl class, register method. Exception: ", e);
            return new ResponseEntity<>("Server error: Something went wrong while trying to register please contact support", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> login(String email, String password) {
        try {
            Optional<User> query = userRepository.findById(email);
            if (query.isPresent()) {
                User user = query.get();
                if (user.checkPass(password))
                    return new ResponseEntity<>(jwtTokenProvider.generateJwtToken(user), HttpStatus.OK);
            }
            // no user found or incorrect email + password match
            return new ResponseEntity<>("The email and password you enter is invalid", HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            logger.error("Something went wrong in the AuthenticationServiceImpl class, login method. Exception: ", e);
            return new ResponseEntity<>("Server error: Something went wrong while trying to login please try again later", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
