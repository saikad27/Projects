package com.example.mychat.repository;

import com.example.mychat.model.MessageDetail;

import java.util.List;

public interface MessageRepoCustom {
    public List<MessageDetail> findMessages(Long client, Long chatUser, int rows);
}
