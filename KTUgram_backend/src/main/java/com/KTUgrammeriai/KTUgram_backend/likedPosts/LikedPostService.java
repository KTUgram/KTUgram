package com.KTUgrammeriai.KTUgram_backend.likedPosts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikedPostService {

    @Autowired
    public LikedPostRepository likedPostRepository;

    public List<LikedPost> getUserLikedPosts(long userId){
        return likedPostRepository.findByUser_IdEquals(userId);
    }

}
