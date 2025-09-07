package com.example.mychat.model;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class LoginCredential {
    private String username;
    private String password;

    public LoginCredential() {
        System.out.println("LoginCredential bean has been created.");
    }
    @PostConstruct
    public void init(){
        System.out.println("LoginCrdential bean initialized");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("LoginCrdential bean destroyed");
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
