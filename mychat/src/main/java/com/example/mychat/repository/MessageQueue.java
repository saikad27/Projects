package com.example.mychat.repository;

import com.example.mychat.model.MessageDetail;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Repository
public class MessageQueue {
    private Queue<MessageDetail> queue;
    MessageQueue(){
        this.queue = new LinkedList<MessageDetail>();
    }
    public void put(MessageDetail messageDetail){
        queue.add(messageDetail);
    }
    public MessageDetail remove(){
        return queue.poll();
    }

    public MessageDetail get(){
        return queue.peek();
    }

    public List<MessageDetail> retrieveAll(Long receiverId){
        List<MessageDetail> messageList= new ArrayList<MessageDetail>();
        for(MessageDetail md : queue){
            if(receiverId.equals(md.getReceiver().getUser_id())){
                messageList.add(md);
            }
        }
        return messageList;
    }
}
