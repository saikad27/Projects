package com.example.mychat.processor;


import com.example.mychat.model.UserDetail;
import com.example.mychat.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserProcessor {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    UserProcessor(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void processUser(String email, String username,String password){
        UserDetail userDetail = new UserDetail();
        userDetail.setUsername(username);
        userDetail.setEmail(email);
        String encodedPassword = passwordEncoder.encode(password);
        userDetail.setPassword(encodedPassword);
        userRepository.save(userDetail);
    }
}
