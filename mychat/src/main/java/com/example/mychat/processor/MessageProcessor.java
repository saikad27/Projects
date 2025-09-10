package com.example.mychat.processor;

import com.example.mychat.model.MessageDetail;
import com.example.mychat.model.QueuedMessage;
import com.example.mychat.model.UserDetail;
import com.example.mychat.model.UserSession;
import com.example.mychat.repository.MessageQueueRepository;
import com.example.mychat.repository.MessageRepository;
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
    private final Map<Long, DeferredResult<MessageDetail>> requestRegistry;


    MessageProcessor(MessageQueueRepository messageQueue, MessageRepository messageRepository, Map<Long,DeferredResult<MessageDetail>> requestRegistry){
        this.messageQueue = messageQueue;
        this.messageRepository = messageRepository;
        this.requestRegistry = requestRegistry;

    }

    public MessageDetail process(String message, Long senderId, Long receiverId){
        MessageDetail messageDetail = new MessageDetail();
        messageDetail.setSender(new UserDetail(senderId));
        messageDetail.setReceiver(new UserDetail(receiverId));
        messageDetail.setMessage(message);
        messageDetail.setMessage_sent_date(Date.valueOf(LocalDate.now()));
        messageDetail.setMessage_sent_time(Time.valueOf(LocalTime.now()));
        messageDetail.setMessage_delivery_status(false);
        messageRepository.save(messageDetail);
        QueuedMessage queuedMessage = new QueuedMessage(messageDetail);
        if(requestRegistry.containsKey(messageDetail.getReceiver().getUser_id())){
            requestRegistry.get(messageDetail.getReceiver().getUser_id()).setResult(messageDetail);
            messageQueue.deleteByReceiverId(messageDetail.getReceiver().getUser_id());
        }
        return messageDetail;
    }

    public List<QueuedMessage> fetchAllMessages(Long receiverId){
        List<QueuedMessage> fetchedMessages= messageQueue.findByReceiverId(receiverId);
        messageQueue.deleteByReceiverId(receiverId);
        return fetchedMessages;
    }

}
