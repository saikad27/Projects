package com.example.mychat.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name="message_queue")
public class QueuedMessage {
    public QueuedMessage(MessageDetail messageDetail){
        messageId = messageDetail.getMessageId();
        message_sent_date = messageDetail.getMessage_sent_date();
        message_sent_time = messageDetail.getMessage_sent_time();
        message = messageDetail.getMessage();
        sender = messageDetail.getSender();
        receiver = messageDetail.getReceiver();
        message_delivery_status = messageDetail.getMessage_delivery_status();
    }
    @Id
    private Long messageId;
    private Date message_sent_date;
    private Time message_sent_time;

    @ManyToOne
    @JoinColumn(name="sender_id" , referencedColumnName="user_id")
    private UserDetail sender;
    private String message;
    private Date message_received_date;
    private Time message_received_time;

    @ManyToOne
    @JoinColumn(name="receiver_id" , referencedColumnName="user_id")
    private UserDetail receiver;
    private Boolean message_delivery_status;
}
