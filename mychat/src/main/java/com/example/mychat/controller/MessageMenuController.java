package com.example.mychat.controller;


import com.example.mychat.dto.ChatUserDTO;
import com.example.mychat.model.UserSession;
import com.example.mychat.repository.MessageRepository;
import com.example.mychat.repository.UserRepository;
import com.example.mychat.service.GetUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MessageMenuController {
    private final UserSession userSession;
    private final GetUserService getUserService;
    MessageMenuController(UserSession userSession,GetUserService getUserService){
        this.userSession = userSession;
        this.getUserService = getUserService;
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
        return getUserService.getChatUsers(userSession.getUserId());
    }
}
