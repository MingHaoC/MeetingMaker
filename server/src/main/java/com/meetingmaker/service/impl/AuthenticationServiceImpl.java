package com.meetingmaker.service.impl;

import com.meetingmaker.component.JwtTokenProvider;
import com.meetingmaker.constant.RoleName;
import com.meetingmaker.entity.EmailVerify;
import com.meetingmaker.entity.Role;
import com.meetingmaker.entity.User;
import com.meetingmaker.repository.EmailVerifyRepo;
import com.meetingmaker.repository.RoleRepo;
import com.meetingmaker.repository.UserRepository;
import com.meetingmaker.service.AuthenticationService;
import com.meetingmaker.util.AppException;
import com.meetingmaker.util.Mailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailVerifyRepo emailVerifyRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    Mailer mailer;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Value("${url}")
    String url;

    @Override
    public ResponseEntity<String> register(User user) {
        Role userRole = roleRepo.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));
        user.setRoles(Collections.singleton(userRole));
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());
        newUser.setRoles(Collections.singleton(userRole));
        newUser.setVerify(false);
        newUser.hashPassword();
        try {
            if (userRepository.findByEmail(newUser.getEmail()).isPresent())
                return new ResponseEntity<>("The email you enter is already registered on our website", HttpStatus.CONFLICT);
            userRepository.save(newUser);
            userRepository.flush();
            UUID uuid = UUID.randomUUID();
            emailVerifyRepo.save(uuid.toString(), newUser.getId());
            String emailVerification = mailer.readTemplateFile("EmailVerification.html");
            emailVerification = emailVerification.replace("%name%", newUser.getFirstName() + " " + newUser.getLastName());
            emailVerification = emailVerification.replace("%activationLink%", url + "/verify/email/" + uuid);
            mailer.sendEmail(emailVerification, "Email Verification", "minghao2@outlook.com");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Something went wrong in the AuthenticationServiceImpl class, register method. Exception: ", e);
            return new ResponseEntity<>("Server error: Something went wrong while trying to register please contact support", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> login(String email, String password) {
        try {
            Optional<User> query = userRepository.findByEmail(email);
            if (query.isPresent()) {
                User user = query.get();
                if (!user.getVerify())
                    return new ResponseEntity<>("The email is not verify, please check your email inbox to verify this account", HttpStatus.UNAUTHORIZED);
                else if (user.checkPass(password)) {
                    return new ResponseEntity<>(jwtTokenProvider.generateJwtToken(user), HttpStatus.OK);
                }
            }
            // no user found or incorrect email + password match
            return new ResponseEntity<>("The email and password you enter is invalid", HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            logger.error("Something went wrong in the AuthenticationServiceImpl class, login method. Exception: ", e);
            return new ResponseEntity<>("Server error: Something went wrong while trying to login please try again later", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> emailVerify(String uuid) {
        Optional<EmailVerify> query = emailVerifyRepo.findByUuid(uuid);
        if (query.isPresent()) {
            EmailVerify em = query.get();
            // verify the email
            userRepository.updateVerify(true, em.getUser().getId());
            // delete the entry after user have been verified
            emailVerifyRepo.delete(em);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The link is invalid", HttpStatus.BAD_REQUEST);
        }
    }
}
