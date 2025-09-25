package com.example.mychat.dto;

import com.example.mychat.model.UserDetail;

public class ChatUserDTO {
    private Long chatUserId;
    private String chatUserName;
    private Long messageCount;

    public ChatUserDTO() {

    }
    public ChatUserDTO(UserDetail userDetail){
        chatUserId = userDetail.getUserId();
        chatUserName = userDetail.getUsername();
        messageCount = 0L;
    }

    public Long getChatUserId() {
        return chatUserId;
    }

    public void setChatUserId(Long chatUserId) {
        this.chatUserId = chatUserId;
    }

    public String getChatUserName() {
        return chatUserName;
    }

    public void setChatUserName(String chatUserName) {
        this.chatUserName = chatUserName;
    }

    public Long getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(Long messageCount) {
        this.messageCount = messageCount;
    }
}
