package com.example.mychat.controller;


import com.example.mychat.model.LoginCredential;
import com.example.mychat.model.QueuedMessage;
import com.example.mychat.model.UserSession;
import com.example.mychat.processor.LoginProcessor;
import com.example.mychat.processor.MessageProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.desktop.UserSessionEvent;
import java.util.List;

@Controller
public class LoginController {

    private final LoginProcessor loginProcessor;
    private final LoginCredential loginCredential;
    private final UserSession userSession;
    private final MessageProcessor messageProcessor;
    LoginController(LoginProcessor loginProcessor,LoginCredential loginCredential,UserSession userSession,MessageProcessor messageProcessor){
        this.loginProcessor = loginProcessor;
        this.loginCredential = loginCredential;
        this.userSession = userSession;
        this.messageProcessor = messageProcessor;
    }
    @GetMapping("/")
    public String loginGet(){
        return "login.html";
    }

    @PostMapping("/")
    public String loginPost(@RequestParam String username,@RequestParam String password,Model model){
        boolean loginResult;
        loginCredential.setUsername(username);
        loginCredential.setPassword(password);
        try{
             loginResult = loginProcessor.processLogin(loginCredential,userSession);
        }catch(RuntimeException e){
            loginResult = false;
            System.out.println("Login failed");
        }
        if(!loginResult){
            return "redirect:/";
        }

        model.addAttribute("username",username);

        return "message_menu.html";
    }
    @ResponseBody
    @PostMapping("/fetchAllMessages")
    public List<QueuedMessage> fetchAllMessages(){
        return messageProcessor.fetchAllMessages(userSession.getUserId());
    }
}
