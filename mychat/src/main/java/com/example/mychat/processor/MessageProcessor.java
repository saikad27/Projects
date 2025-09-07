package com.example.mychat.processor;

import com.example.mychat.model.MessageDetail;
import com.example.mychat.model.UserDetail;
import com.example.mychat.repository.MessageQueue;
import com.example.mychat.repository.MessageRepository;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
public class MessageProcessor {
    private final MessageQueue messageQueue;
    private final MessageRepository messageRepository;

    MessageProcessor(MessageQueue messageQueue,MessageRepository messageRepository){
        this.messageQueue = messageQueue;
        this.messageRepository = messageRepository;
    }

    public MessageDetail process(String message, Long senderId, Long receiverId){
        MessageDetail messageDetail = new MessageDetail();
        messageDetail.setSender(new UserDetail(senderId));
        messageDetail.setReceiver(new UserDetail(receiverId));
        messageDetail.setMessage(message);
        messageDetail.setMessage_sent_date(Date.valueOf(LocalDate.now()));
        messageDetail.setMessage_sent_time(Time.valueOf(LocalTime.now()));
        messageDetail.setMessage_sent_status(true);
        messageQueue.put(messageDetail);
        messageRepository.save(messageQueue.get());
        return messageDetail;
    }

    public List<MessageDetail> retrieveAll(Long receiverId){
        return messageQueue.retrieveAll(receiverId);
    }


}
