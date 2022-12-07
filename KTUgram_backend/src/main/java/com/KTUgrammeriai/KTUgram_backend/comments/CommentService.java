package com.KTUgrammeriai.KTUgram_backend.comments;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    public CommentRepository commentRepository;

    public List<Comment> getPostComments(long postId){
        return commentRepository.findByPost_IdEquals(postId);
    }

    public List<Comment> getPostCommentsByUser(long postId, long userId){
        return commentRepository.findByPost_IdEqualsAndUser_IdEquals(postId, userId);
    }

    public List<Comment> getPostCommentsExceptUser(long postId, long userId){
        return commentRepository.findByPost_IdEqualsAndUser_IdIsNot(postId, userId);
    }

}
