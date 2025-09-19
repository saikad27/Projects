package com.example.mychat.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name="message_queue")
public class QueuedMessage {
    public QueuedMessage(MessageDetail messageDetail){
        messageId = messageDetail.getMessageId();
        messageSentDate = messageDetail.getMessage_sent_date();
        messageSentTime = messageDetail.getMessage_sent_time();
        message = messageDetail.getMessage();
        sender = messageDetail.getSender();
        receiver = messageDetail.getReceiver();
        messageDeliveryStatus = messageDetail.getMessage_delivery_status();
    }

    public QueuedMessage() {
    }

    @Id
    private Long messageId;
    private Date messageSentDate;
    private Time messageSentTime;

    @ManyToOne
    @JoinColumn(name="sender_id" , referencedColumnName="userId")
    private UserDetail sender;
    private String message;
    private Date messageReceivedDate;
    private Time messageReceivedTime;

    @ManyToOne
    @JoinColumn(name="receiver_id" , referencedColumnName="userId")
    private UserDetail receiver;
    private Boolean messageDeliveryStatus;

    @Override
    public String toString() {
        return "QueuedMessage{" +
                "messageId=" + messageId +
                ", message_sent_date=" + messageSentDate +
                ", message_sent_time=" + messageSentTime +
                ", sender=" + sender +
                ", message='" + message + '\'' +
                ", message_received_date=" + messageReceivedDate +
                ", message_received_time=" + messageReceivedTime +
                ", receiver=" + receiver +
                ", message_delivery_status=" + messageDeliveryStatus +
                '}';
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Date getMessage_sent_date() {
        return messageSentDate;
    }

    public void setMessage_sent_date(Date messageSentDate) {
        this.messageSentDate = messageSentDate;
    }

    public Time getMessage_sent_time() {
        return messageSentTime;
    }

    public void setMessage_sent_time(Time messageSentTime) {
        this.messageSentTime = messageSentTime;
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
        return messageReceivedDate;
    }

    public void setMessage_received_date(Date message_received_date) {
        this.messageReceivedDate = message_received_date;
    }

    public Time getMessage_received_time() {
        return messageReceivedTime;
    }

    public void setMessage_received_time(Time messageReceivedTime) {
        this.messageReceivedTime = messageReceivedTime;
    }

    public UserDetail getReceiver() {
        return receiver;
    }

    public void setReceiver(UserDetail receiver) {
        this.receiver = receiver;
    }

    public Boolean getMessage_delivery_status() {
        return messageDeliveryStatus;
    }

    public void setMessage_delivery_status(Boolean messageDeliveryStatus) {
        this.messageDeliveryStatus = messageDeliveryStatus;
    }
}
