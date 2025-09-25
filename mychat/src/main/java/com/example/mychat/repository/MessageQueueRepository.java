package com.example.mychat.repository;

import com.example.mychat.model.QueuedMessage;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageQueueRepository extends JpaRepository<QueuedMessage,Long> {

    @Query("SELECT qm FROM QueuedMessage qm WHERE qm.sender.userId=:sender_id AND qm.receiver.userId=:receiver_id")
    public List<QueuedMessage> getChatMessages(@Param("sender_id") Long senderId,@Param("receiver_id") Long receiverId);

    @Transactional
    @Query("DELETE FROM QueuedMessage qm WHERE qm.receiver.userId=:receiver_id AND qm.sender.userId=:sender_id")
    @Modifying
    public int deleteChatMessages(@Param("receiver_id") Long receiverId,@Param("sender_id") Long senderId);

    @Modifying
    @Transactional
    @Query("UPDATE QueuedMessage qm SET qm.messageReceivedDate=CURRENT_DATE(),qm.messageReceivedTime=CURRENT_TIME(),qm.messageDeliveryStatus=true WHERE qm.receiver.userId=:receiverId")
    public void updateMessageRetrievalDetails(@Param("receiverId") Long receiverId);

    @Query("SELECT COUNT(qm) FROM QueuedMessage qm WHERE qm.sender.userId=:senderId")
    public Long getMessageCount(@Param("senderId") Long senderId);
}
