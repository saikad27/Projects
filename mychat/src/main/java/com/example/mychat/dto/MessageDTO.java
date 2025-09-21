package com.example.mychat.dto;

import com.example.mychat.model.MessageDetail;

public class MessageDTO {
    private Long receiverId;
    private Long senderId;
    private String message;

    public MessageDTO() {
    }

    public MessageDTO(MessageDetail messageDetail) {
        this.receiverId = messageDetail.getReceiver().getUserId();
        this.senderId = messageDetail.getSender().getUserId();
        this.message = messageDetail.getMessage();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "receiverId=" + receiverId +
                ", senderId=" + senderId +
                ", message='" + message + '\'' +
                '}';
    }
}
