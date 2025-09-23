package com.example.mychat.service;

import com.example.mychat.dto.ChatUserDTO;
import com.example.mychat.repository.MessageQueueRepository;
import com.example.mychat.repository.MessageRepository;
import com.example.mychat.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class GetUserService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final MessageQueueRepository messageQueue;
    public GetUserService(MessageRepository messageRepository, UserRepository userRepository, MessageQueueRepository messageQueue){
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.messageQueue = messageQueue;
    }

    public List<ChatUserDTO> getChatUsers(Long userId){
        List<Long> idList = messageRepository.getChatUsers(userId);
        List<ChatUserDTO> userList = new ArrayList<>();
        for(Long id : idList){
            ChatUserDTO chatUserDTO = new ChatUserDTO();
            chatUserDTO.setChatUserId(id);
            chatUserDTO.setChatUserName(userRepository.getReferenceById(id).getUsername());
            chatUserDTO.setMessageCount(messageQueue.getMessageCount(chatUserDTO.getChatUserId()));
            System.out.println(chatUserDTO);
            userList.add(chatUserDTO);
        }
        return userList;
    }
}
