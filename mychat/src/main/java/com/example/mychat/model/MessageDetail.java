package com.example.mychat.model;

import jakarta.persistence.*;
import org.springframework.web.context.annotation.RequestScope;

import java.sql.Date;
import java.sql.Time;

@Entity
public class MessageDetail {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long messageId;
    private Date message_sent_date;
    private Time message_sent_time;

    @ManyToOne
    @JoinColumn(name="sender_id" , referencedColumnName="userId")
    private UserDetail sender;
    private String message;
    private Date message_received_date;
    private Time message_received_time;

    @ManyToOne
    @JoinColumn(name="receiver_id" , referencedColumnName="userId")
    private UserDetail receiver;
    private Boolean message_delivery_status;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Date getMessage_sent_date() {
        return message_sent_date;
    }

    public void setMessage_sent_date(Date message_sent_date) {
        this.message_sent_date = message_sent_date;
    }

    public Time getMessage_sent_time() {
        return message_sent_time;
    }

    public void setMessage_sent_time(Time message_sent_time) {
        this.message_sent_time = message_sent_time;
    }

    public UserDetail getSender() {
        return sender;
    }

    public void setSender(UserDetail sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getMessage_received_date() {
        return message_received_date;
    }

    public void setMessage_received_date(Date message_received_date) {
        this.message_received_date = message_received_date;
    }

    public Time getMessage_received_time() {
        return message_received_time;
    }

    public void setMessage_received_time(Time message_received_time) {
        this.message_received_time = message_received_time;
    }

    public UserDetail getReceiver() {
        return receiver;
    }

    public void setReceiver(UserDetail receiver) {
        this.receiver = receiver;
    }

    public Boolean getMessage_delivery_status() {
        return message_delivery_status;
    }

    public void setMessage_delivery_status(Boolean message_received_status) {
        this.message_delivery_status = message_received_status;
    }
}
