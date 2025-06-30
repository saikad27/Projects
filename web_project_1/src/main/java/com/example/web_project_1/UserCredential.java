package com.example.web_project_1;




public class UserCredential {

    public UserCredential(String username, String email, int mobile, String password) {
        this.username = username;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
    }

    public UserCredential(){

    }

    private String username;

    private String email;

    private int mobile;

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

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
