package com.example.mychat.repository;

import com.example.mychat.model.MessageDetail;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessageRepository extends JpaRepository<MessageDetail,Long>,MessageRepoCustom {
    @Modifying
    @Transactional
    @Query("UPDATE QueuedMessage qm SET qm.messageReceivedDate=CURRENT_DATE(),qm.messageReceivedTime=CURRENT_TIME(),qm.messageDeliveryStatus=true WHERE qm.receiver.userId=:receiverId AND qm.messageDeliveryStatus=false")
    public int updateMessageRetrievalDetails(@Param("receiverId") Long receiverId);
}
