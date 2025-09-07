package com.example.mychat.controller;

import com.example.mychat.exception.UserNotFoundException;
import com.example.mychat.model.MessageDetail;
import com.example.mychat.model.UserSession;
import com.example.mychat.processor.MessageProcessor;
import com.example.mychat.service.UserVerificationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessageController {
    private final UserVerificationService userVerifier;
    private final MessageProcessor messageProcessor;
    private UserSession userSession;
    public MessageController(UserVerificationService userVerifier,MessageProcessor messageProcessor,UserSession userSession) {
        this.userVerifier = userVerifier;
        this.messageProcessor = messageProcessor;
        this.userSession = userSession;
    }

    @PostMapping("/home/verify")
    public String verify(@RequestParam String receiver, Model model){

        Long receiverId = null;
        boolean verificationResult;
        try {
            receiverId = userVerifier.verifyUser(receiver);
            verificationResult = true;
        }catch(UserNotFoundException e){
            e.printStackTrace();
            verificationResult = false;
        }
        if(verificationResult){
            model.addAttribute("message","verified");
            model.addAttribute("receiver",receiver);
            model.addAttribute("receiver_id",receiverId);
        }else{
            model.addAttribute("message","not verified");
        }
        return "message.html";
    }

    @PostMapping("/message/send")
    public String sendMessage(@RequestParam String message, @RequestParam Long receiverId,Model model){
        MessageDetail messageDetail = messageProcessor.process(message,userSession.getUserId(),receiverId);
        return "message.html";
    }


}
