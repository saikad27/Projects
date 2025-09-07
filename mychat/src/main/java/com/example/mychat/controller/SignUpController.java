package com.example.mychat.controller;


import com.example.mychat.processor.UserProcessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignUpController {
    private final UserProcessor userProcessor;
    SignUpController(UserProcessor userProcessor){
        this.userProcessor = userProcessor;
    }
    @GetMapping("/signup")
    public String signUpGet(){
        return "register.html";
    }

    @PostMapping("/signup")
    public String signUpPost(@RequestParam String email,@RequestParam String username,@RequestParam String password,@RequestParam String confirm_password){

        if(!password.equals(confirm_password)){
            return "redirect:/signup";
        }
        userProcessor.processUser(email,username,password);
        return "login.html";
    }
}
