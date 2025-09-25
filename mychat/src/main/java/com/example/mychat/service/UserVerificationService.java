package com.example.mychat.service;

import com.example.mychat.dto.ChatUserDTO;
import com.example.mychat.exception.UserNotFoundException;
import com.example.mychat.model.UserDetail;
import com.example.mychat.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserVerificationService {
    private final UserRepository userRepository;

    public UserVerificationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ChatUserDTO verifyUser(String user){
        UserDetail userDetail = userRepository.findByUsername(user).orElseThrow(()-> new UserNotFoundException("User not found with name : "+user));
        return new ChatUserDTO(userDetail);
    }
}
