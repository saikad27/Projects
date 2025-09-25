package com.example.mychat.controller;

import com.example.mychat.dto.MessageDTO;
import com.example.mychat.model.UserSession;
import com.example.mychat.processor.MessageProcessor;
import com.example.mychat.service.UserVerificationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import java.util.List;
import java.util.Map;

@Controller
public class MessageController {
    private final UserVerificationService userVerifier;
    private final MessageProcessor messageProcessor;
    private final UserSession userSession;
    private final Map<Long,DeferredResult<MessageDTO>> requestRegistry;
    public MessageController(UserVerificationService userVerifier,MessageProcessor messageProcessor,UserSession userSession,Map<Long,DeferredResult<MessageDTO>> requestRegistry) {
        this.userVerifier = userVerifier;
        this.messageProcessor = messageProcessor;
        this.userSession = userSession;
        this.requestRegistry = requestRegistry;
    }

    /*@GetMapping("/user/verify")
    public String verify(@RequestParam String chatUserName, Model model){

        Long chatUserId = null;
        boolean verificationResult;
        try {
            chatUserId = userVerifier.verifyUser(chatUserName);
            verificationResult = true;
        }catch(UserNotFoundException e){
            e.printStackTrace();
            verificationResult = false;
        }
        if(verificationResult){
            List<MessageDTO> messageList = messageProcessor.retrieveFirstNMessages(userSession.getUserId(),chatUserId);
            model.addAttribute("chatUserName",chatUserName);
            model.addAttribute("chatUserId",chatUserId);
            model.addAttribute("messageList",messageList);
            for(MessageDTO message : messageList){
                System.out.println(message);
            }
            return "chat_box.html";
        }else{
            model.addAttribute("message","not verified");
            return "redirect:/message_menu";
        }
    }*/

    @ResponseBody
    @PostMapping("/message/send")
    public MessageDTO sendMessage(@RequestBody MessageDTO messageDTOSent){
        System.out.println(userSession.getUserId()+" sent a message to "+messageDTOSent.getReceiverId());
        return messageProcessor.process(messageDTOSent);
    }

    @ResponseBody
    @PostMapping("/message/fetch")
    public DeferredResult<MessageDTO> fetchMessage(){
        Long userId = userSession.getUserId();
        DeferredResult<MessageDTO> dr = new DeferredResult<>(30000L);
        requestRegistry.put(userSession.getUserId(),dr);
        System.out.println("Online user registry contains : "+requestRegistry.keySet());
        //messageProcessor.fetchMessage(dr);
        dr.onCompletion(() -> requestRegistry.remove(userId));
        dr.onTimeout(() -> requestRegistry.remove(userId));
        return dr;
    }


    @ResponseBody
    @PostMapping("/message/fetch_messages")
    public List<MessageDTO> fetchNMessages(@RequestParam Long chatUserId){
        return messageProcessor.retrieveFirstNMessages(userSession.getUserId(),chatUserId);
    }

    @GetMapping("/message/chat")
    public String getChatBox(){
        return "chat_box";
    }

    @PostMapping("/remove_user")
    public void removeUserFromRegistry(){
        requestRegistry.remove(userSession.getUserId());
        System.out.println(userSession.getUserName()+" is removed from in chat user registry with id : "+userSession.getUserId());
    }

}
