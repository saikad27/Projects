package com.example.mychat.service;

import com.example.mychat.exception.UserNotFoundException;
import com.example.mychat.model.UserDetail;
import com.example.mychat.model.UserSession;
import com.example.mychat.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserVerificationService {
    private final UserRepository userRepository;

    public UserVerificationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long verifyUser(String user){
           return userRepository.findByUsername(user).orElseThrow(()-> new UserNotFoundException("User not found with name : "+user)).getUserId();
    }
}
