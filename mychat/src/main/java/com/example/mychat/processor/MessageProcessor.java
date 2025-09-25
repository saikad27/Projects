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
        messageDetail.setMessageSentDate(Date.valueOf(LocalDate.now()));
        messageDetail.setMessageSentTime(Time.valueOf(LocalTime.now()));
        messageDetail.setMessageDeliveryStatus(false);
        messageDetail.setDeleted(false);
        messageRepository.save(messageDetail);
        QueuedMessage queuedMessage = new QueuedMessage(messageDetail);
        System.out.println("Sent message is processed : "+queuedMessage);
        messageQueue.save(queuedMessage);
        System.out.println("Online user registry contains : "+requestRegistry.keySet());
        System.out.println(messageDetail.getReceiver().getUsername()+" is online : "+requestRegistry.containsKey(messageDTO.getReceiverId()));
        if(requestRegistry.containsKey(messageDTO.getReceiverId())){
            System.out.println("Receiver is about to retrieve the message");
            messageRepository.updateMessageRetrievalDetails(messageDTO.getReceiverId());
            requestRegistry.remove(messageDTO.getReceiverId()).setResult(messageDTO);
            messageQueue.deleteChatMessages(messageDTO.getReceiverId(),messageDTO.getSenderId());
        }
        return messageDTO;
    }

    /*public List<QueuedMessage> fetchAllMessages(Long senderId){
        List<QueuedMessage> fetchedMessages= messageQueue.findByReceiverId(receiverId);
        messageQueue.deleteByReceiverId(receiverId);
        return fetchedMessages;
    }*/

    public List<MessageDTO> retrieveFirstNMessages(Long clientId,Long chatUserId){

        messageRepository.updateMessageRetrievalDetails(clientId);
        int deletedMessages = messageQueue.deleteChatMessages(clientId,chatUserId);
        int totalMessagesToRetrieve = 50;
        while(deletedMessages>totalMessagesToRetrieve){
            totalMessagesToRetrieve+=10;
        }
       return messageRepository.findMessages(clientId,chatUserId,totalMessagesToRetrieve);
    }

}
