package com.example.mychat.processor;


import com.example.mychat.model.LoginCredential;
import com.example.mychat.model.UserDetail;
import com.example.mychat.model.UserSession;
import com.example.mychat.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class LoginProcessor {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    LoginProcessor(UserRepository userRepository,BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public boolean processLogin(LoginCredential loginCred, UserSession userSession) {
        UserDetail userDetail = userRepository.findByUsername(loginCred.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        boolean loginResult = loginCred.getUsername().equals(userDetail.getUsername()) && authenticatePassword(userDetail,loginCred.getPassword());
        if(loginResult) {
            userSession.setUserId(userDetail.getUserId());
            userSession.setUserName(userDetail.getUsername());
            userSession.setEmail(userDetail.getEmail());
        }
        return loginResult;
    }
    private boolean authenticatePassword(UserDetail userDetail,String password){
        return passwordEncoder.matches(password,userDetail.getPassword());
    }
}
