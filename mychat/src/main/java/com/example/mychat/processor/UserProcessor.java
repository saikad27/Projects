package com.example.mychat.processor;


import com.example.mychat.model.UserDetail;
import com.example.mychat.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserProcessor {
    private final UserRepository userRepository;
    UserProcessor(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void processUser(String email, String username,String password){
        UserDetail userDetail = new UserDetail();
        userDetail.setUsername(username);
        userDetail.setEmail(email);
        userDetail.setPassword(password);
        userRepository.save(userDetail);
    }
}
