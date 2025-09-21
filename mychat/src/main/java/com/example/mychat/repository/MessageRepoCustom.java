package com.example.mychat.repository;

import com.example.mychat.dto.MessageDTO;
import com.example.mychat.model.MessageDetail;

import java.util.List;

public interface MessageRepoCustom {
    public List<MessageDTO> findMessages(Long client, Long chatUser, int rows);
}
