package com.example.mychat.model;

import jakarta.persistence.*;
import org.springframework.web.context.annotation.RequestScope;

@Entity
public class UserDetail {
    public UserDetail() {
    }

    public UserDetail(Long user_id) {
        this.user_id = user_id;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long user_id;
    @Column(name="username")
    private String username;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}