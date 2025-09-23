package com.example.mychat.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name="new_message_queue")
public class QueuedMessage {
    public QueuedMessage(MessageDetail messageDetail){
        messageId = messageDetail.getMessageId();
        messageSentDate = messageDetail.getMessageSentDate();
        messageSentTime = messageDetail.getMessageSentTime();
        message = messageDetail.getMessage();
        sender = messageDetail.getSender();
        receiver = messageDetail.getReceiver();
        messageDeliveryStatus = messageDetail.getMessageDeliveryStatus();
        isDeleted = messageDetail.getDeleted();
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
    @Column(name="message_delivered")
    private Boolean messageDeliveryStatus;
    @Column(name="deleted")
    private Boolean isDeleted;

    @Override
    public String toString() {
        return "QueuedMessage{" +
                "messageId=" + messageId +
                ", messageSentDate=" + messageSentDate +
                ", messageSentTime=" + messageSentTime +
                ", sender=" + sender +
                ", message='" + message + '\'' +
                ", messageReceivedDate=" + messageReceivedDate +
                ", messageReceivedTime=" + messageReceivedTime +
                ", receiver=" + receiver +
                ", messageDeliveryStatus=" + messageDeliveryStatus +
                ", isDeleted=" + isDeleted +
                '}';
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Date getMessageSentDate() {
        return messageSentDate;
    }

    public void setMessageSentDate(Date messageSentDate) {
        this.messageSentDate = messageSentDate;
    }

    public Time getMessageSentTime() {
        return messageSentTime;
    }

    public void setMessageSentTime(Time messageSentTime) {
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

    public Date getMessageReceivedDate() {
        return messageReceivedDate;
    }

    public void setMessageReceivedDate(Date messageReceivedDate) {
        this.messageReceivedDate = messageReceivedDate;
    }

    public Time getMessageReceivedTime() {
        return messageReceivedTime;
    }

    public void setMessageReceivedTime(Time messageReceivedTime) {
        this.messageReceivedTime = messageReceivedTime;
    }

    public UserDetail getReceiver() {
        return receiver;
    }

    public void setReceiver(UserDetail receiver) {
        this.receiver = receiver;
    }

    public Boolean getMessageDeliveryStatus() {
        return messageDeliveryStatus;
    }

    public void setMessageDeliveryStatus(Boolean messageDeliveryStatus) {
        this.messageDeliveryStatus = messageDeliveryStatus;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
