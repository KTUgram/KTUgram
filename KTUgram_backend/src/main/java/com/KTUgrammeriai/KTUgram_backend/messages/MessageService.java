package com.KTUgrammeriai.KTUgram_backend.messages;


import com.KTUgrammeriai.KTUgram_backend.comments.Comment;
import com.KTUgrammeriai.KTUgram_backend.post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    public MessageRepository MessageRepository;


    public List<Message> getAllMessages(){
        return (List<Message>) MessageRepository.findAll();
    }



}
