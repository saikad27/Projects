package com.example.mychat.controller;

import com.example.mychat.dto.MessageDTO;
import com.example.mychat.exception.UserNotFoundException;
import com.example.mychat.model.MessageDetail;
import com.example.mychat.model.QueuedMessage;
import com.example.mychat.model.UserSession;
import com.example.mychat.processor.MessageProcessor;
import com.example.mychat.service.UserVerificationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import java.util.List;
import java.util.Map;

@Controller
public class MessageController {
    private final UserVerificationService userVerifier;
    private final MessageProcessor messageProcessor;
    private final UserSession userSession;
    private final Map<Long,DeferredResult<MessageDetail>> requestRegistry;
    public MessageController(UserVerificationService userVerifier,MessageProcessor messageProcessor,UserSession userSession,Map<Long,DeferredResult<MessageDetail>> requestRegistry) {
        this.userVerifier = userVerifier;
        this.messageProcessor = messageProcessor;
        this.userSession = userSession;
        this.requestRegistry = requestRegistry;
    }

    @PostMapping("/user/verify")
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
            List<MessageDetail> messageList = messageProcessor.retrieveFirstNMessages(userSession.getUserId(),chatUserId);
            model.addAttribute("chatUserName",chatUserName);
            model.addAttribute("chatUserId",chatUserId);
            model.addAttribute("messageList",messageList);
            for(MessageDetail message : messageList){
                System.out.println(message);
            }
            return "chat_box.html";
        }else{
            model.addAttribute("message","not verified");
            return "message_menu.html";
        }
    }

    @ResponseBody
    @PostMapping("/message/send")
    public MessageDTO sendMessage(@RequestBody MessageDTO messageDTO){
        System.out.println(messageDTO);
        MessageDetail messageDetail = messageProcessor.process(messageDTO.getMessage(),userSession.getUserId(),messageDTO.getReceiverId());
        return messageDTO;
    }

    @ResponseBody
    @PostMapping("/message/fetch")
    public DeferredResult<MessageDetail> fetchMessage(){
        DeferredResult<MessageDetail> dr = new DeferredResult<>(30000L);
        requestRegistry.put(userSession.getUserId(),dr);
        dr.onCompletion(() -> requestRegistry.remove(userSession.getUserId()));
        return dr;
    }

    @ResponseBody
    @PostMapping("/message/fetchAll")
    public List<QueuedMessage> fetchAllMessages(Long receiverId){
        return messageProcessor.fetchAllMessages(userSession.getUserId());
    }

}
