package com.example.mychat.repository;

import com.example.mychat.model.QueuedMessage;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageQueueRepository extends JpaRepository<QueuedMessage,Long> {

    @Query("SELECT qm FROM QueuedMessage qm WHERE qm.receiver.userId=:receiver_id")
    public List<QueuedMessage> findByReceiverId(@Param("receiver_id") Long receiver_id);

    @Transactional
    @Query("DELETE FROM QueuedMessage qm WHERE qm.receiver.userId=:receiver_id")
    @Modifying
    public void deleteByReceiverId(@Param("receiver_id") Long receiver_id);



}
