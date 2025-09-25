package com.example.mychat.repository;


import com.example.mychat.dto.MessageDTO;
import com.example.mychat.model.MessageDetail;
import com.example.mychat.model.UserDetail;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageRepositoryImpl implements MessageRepoCustom{

    public List<MessageDTO> findMessages(Long clientId, Long chatUserId, int rows){
        List<MessageDTO> messageList = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mychatdb", "root", "Saikad@1234");
            String query = "SELECT * FROM (SELECT * FROM message_db md WHERE (sender_id=? AND receiver_id=?) OR (sender_id=? AND receiver_id=?) ORDER BY message_id DESC LIMIT ?) result_set ORDER BY message_id ASC";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setLong(1,clientId);
            ps.setLong(2,chatUserId);
            ps.setLong(3,chatUserId);
            ps.setLong(4,clientId);
            ps.setInt(5,rows);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                MessageDetail md = new MessageDetail();
                md.setMessageId(rs.getLong("message_id"));
                md.setMessage(rs.getString("message"));
                md.setMessageReceivedDate(rs.getDate("message_received_date"));
                md.setMessageReceivedTime(rs.getTime("message_received_time"));
                md.setMessageSentDate(rs.getDate("message_sent_date"));
                md.setMessageSentTime(rs.getTime("message_received_time"));
                md.setReceiver(new UserDetail(rs.getLong("receiver_id")));
                md.setSender(new UserDetail(rs.getLong("sender_id")));
                md.setMessageDeliveryStatus(rs.getBoolean("message_delivered"));
                md.setDeleted(rs.getBoolean("deleted"));
                messageList.add(new MessageDTO(md));
            }
            rs.close();
            ps.close();
            conn.close();
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return messageList;
    }
}
