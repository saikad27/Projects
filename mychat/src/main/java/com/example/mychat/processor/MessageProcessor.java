package com.example.mychat.processor;

import com.example.mychat.dto.MessageDTO;
import com.example.mychat.model.MessageDetail;
import com.example.mychat.model.QueuedMessage;
import com.example.mychat.repository.MessageQueueRepository;
import com.example.mychat.repository.MessageRepository;
import com.example.mychat.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Component
public class MessageProcessor {
    private final MessageQueueRepository messageQueue;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final Map<Long, DeferredResult<MessageDTO>> requestRegistry;


    MessageProcessor(MessageQueueRepository messageQueue, MessageRepository messageRepository, Map<Long,DeferredResult<MessageDTO>> requestRegistry,UserRepository userRepository){
        this.messageQueue = messageQueue;
        this.messageRepository = messageRepository;
        this.requestRegistry = requestRegistry;
        this.userRepository = userRepository;

    }

    public MessageDTO process(MessageDTO messageDTO){

        MessageDetail messageDetail = new MessageDetail();
        messageDetail.setSender(userRepository.getReferenceById(messageDTO.getSenderId()));
        messageDetail.setReceiver(userRepository.getReferenceById(messageDTO.getReceiverId()));
        messageDetail.setMessage(messageDTO.getMessage());
        messageDetail.setMessage_sent_date(Date.valueOf(LocalDate.now()));
        messageDetail.setMessage_sent_time(Time.valueOf(LocalTime.now()));
        messageDetail.setMessage_delivery_status(false);
        messageRepository.save(messageDetail);
        QueuedMessage queuedMessage = new QueuedMessage(messageDetail);
        System.out.println(queuedMessage);
        messageQueue.save(queuedMessage);
        //MessageDTO messageDTO = new MessageDTO(messageDetail);
        System.out.println("Online user registry contains : "+requestRegistry.keySet());
        System.out.println(messageDetail.getReceiver().getUsername()+" is online : "+requestRegistry.containsKey(messageDTO.getReceiverId()));
        if(requestRegistry.containsKey(messageDTO.getReceiverId())){
            //requestRegistry.get(messageDetail.getReceiver().getUserId()).setResult(messageDTO);
            requestRegistry.remove(messageDTO.getReceiverId()).setResult(messageDTO);
            messageQueue.deleteByReceiverId(messageDetail.getReceiver().getUserId());
        }
        return messageDTO;
    }

    public List<QueuedMessage> fetchAllMessages(Long receiverId){
        List<QueuedMessage> fetchedMessages= messageQueue.findByReceiverId(receiverId);
        messageQueue.deleteByReceiverId(receiverId);
        return fetchedMessages;
    }

    public List<MessageDTO> retrieveFirstNMessages(Long client,Long chatUser){
        messageQueue.updateMessageRetrievalDetails(client);
        messageRepository.updateMessageRetrievalDetails(client);
        int deletedMessages = messageQueue.deleteByReceiverId(client);
        int totalMessagesToRetrieve = 50;
        while(deletedMessages>totalMessagesToRetrieve){
            totalMessagesToRetrieve+=10;
        }
       return messageRepository.findMessages(client,chatUser,totalMessagesToRetrieve);
    }

}
