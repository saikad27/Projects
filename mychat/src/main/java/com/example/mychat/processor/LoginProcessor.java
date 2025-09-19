package com.example.mychat.processor;


import com.example.mychat.model.LoginCredential;
import com.example.mychat.model.UserDetail;
import com.example.mychat.model.UserSession;
import com.example.mychat.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class LoginProcessor {
    private final UserRepository userRepository;
    LoginProcessor(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public boolean processLogin(LoginCredential loginCred, UserSession userSession) {
        UserDetail userDetail = userRepository.findByUsername(loginCred.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        boolean loginResult = loginCred.getUsername().equals(userDetail.getUsername()) && loginCred.getPassword().equals(userDetail.getPassword());
        if(loginResult) {
            userSession.setUserId(userDetail.getUserId());
            userSession.setUserName(userDetail.getUsername());
            userSession.setEmail(userDetail.getEmail());
        }
        return loginResult;
    }
}
