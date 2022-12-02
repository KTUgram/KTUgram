package com.KTUgrammeriai.KTUgram_backend.comments;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    public CommentRepository commentRepository;

    public List<Comment> getPostComments(long postId){
        return commentRepository.findByPost_IdEquals(postId);
    }
}
