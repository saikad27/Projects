package com.example.mychat.repository;

import com.example.mychat.model.MessageDetail;
import com.example.mychat.model.QueuedMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageQueueRepository extends JpaRepository<QueuedMessage,Long> {

    @Query("SELECT * FROM message_queue WHERE receiver_id=:receiver_id")
    public List<QueuedMessage> findByReceiverId(Long receiver_id);

    @Query("DELETE FROM message_queue WHERE receiver_id:receiver_id")
    public void deleteByReceiverId(Long receiverId);

}
