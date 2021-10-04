package com.meetingmaker.service.impl;


import com.meetingmaker.entity.User;
import com.meetingmaker.entity.UserDetailsImpl;
import com.meetingmaker.repository.UserRepository;
import com.meetingmaker.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(email);
        if(user.isPresent()) {
            return UserDetailsImpl.build(user.get());
        } else {
            throw new UsernameNotFoundException("User not found with the email: " + email);
        }
    }
}
