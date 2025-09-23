package com.example.mychat.repository;

import com.example.mychat.model.MessageDetail;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageDetail,Long>,MessageRepoCustom {
    @Modifying
    @Transactional
    @Query("UPDATE MessageDetail md SET md.messageReceivedDate=CURRENT_DATE,md.messageReceivedTime=CURRENT_TIME,md.messageDeliveryStatus=true WHERE md.receiver.userId=:receiverId AND md.messageDeliveryStatus=false")
    public void updateMessageRetrievalDetails(@Param("receiverId") Long receiverId);

    @Query(value="SELECT receiver_id AS user_id FROM message_db WHERE sender_id = :userId UNION SELECT sender_id AS user_id FROM message_db WHERE receiver_id= :userId",nativeQuery=true)
    public List<Long> getChatUsers(@Param("userId")Long userId);
}
