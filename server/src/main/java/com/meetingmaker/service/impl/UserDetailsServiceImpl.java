package com.meetingmaker.service.impl;


import com.meetingmaker.entity.User;
import com.meetingmaker.repository.UserRepository;
import com.meetingmaker.service.UserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(Integer.parseInt(username));
        if (user.isPresent())
            return UserDetailsImpl.build(user.get());
        else
            throw new UsernameNotFoundException("User not found with the email: " + username);
    }
}
