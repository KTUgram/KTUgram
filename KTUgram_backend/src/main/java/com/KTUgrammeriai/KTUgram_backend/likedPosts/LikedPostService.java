package com.KTUgrammeriai.KTUgram_backend.likedPosts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikedPostService {

    @Autowired
    public LikedPostRepository likedPostRepository;

    public List<LikedPost> getUserLikedPosts(long userId){
        return likedPostRepository.findByUser_IdEquals(userId);
    }

    public Optional<LikedPost> findUserLikedPost(long userID, long postId){
        return likedPostRepository.findByPost_IdAndUser_Id(postId, userID);
    }

    public void save(LikedPost post){
        likedPostRepository.save(post);
    }

    public void delete(LikedPost post){
        likedPostRepository.delete(post);
    }

}
