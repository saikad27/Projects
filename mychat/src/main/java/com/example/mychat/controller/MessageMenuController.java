package com.example.mychat.controller;


import com.example.mychat.dto.ChatUserDTO;
import com.example.mychat.exception.UserNotFoundException;
import com.example.mychat.model.UserSession;
import com.example.mychat.service.GetUserService;
import com.example.mychat.service.UserVerificationService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class MessageMenuController {
    private final UserSession userSession;
    private final GetUserService userService;
    private final UserVerificationService userVerificationService;
    MessageMenuController(UserSession userSession,GetUserService UserService,UserVerificationService userVerificationService){
        this.userSession = userSession;
        this.userService = UserService;
        this.userVerificationService = userVerificationService;
    }
    @GetMapping("/message_menu")
    public String getMessageMenu(Model model){
        model.addAttribute("userName",userSession.getUserName());
        model.addAttribute("userId",userSession.getUserId());
        return "message_menu";
    }

    @ResponseBody
    @GetMapping("/message_menu/fetch_chat_users")
    public List<ChatUserDTO> fetchChatUsers(){
        return userService.getChatUsers(userSession.getUserId());
    }

    @GetMapping("/message_menu/get_chat")
    public String getChat(){
        return "chat_box";
    }

    @ResponseBody
    @GetMapping("/user/verify")
    public ChatUserDTO verifyUser(@RequestParam String user){

        try{
            return userVerificationService.verifyUser(user);
        }catch(UserNotFoundException unfe){
            return new ChatUserDTO();
        }

    }


}
