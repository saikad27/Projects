package com.example.mychat.repository;

import com.example.mychat.model.MessageDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageDetail,Long> {

}
