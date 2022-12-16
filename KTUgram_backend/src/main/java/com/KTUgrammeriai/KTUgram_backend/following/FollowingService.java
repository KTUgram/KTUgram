package com.KTUgrammeriai.KTUgram_backend.following;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FollowingService {

    @Autowired
    public FollowingRepository followingRepository;

    public List<Following> getFollowingById(long id){
        return followingRepository.findByFollower_IdEquals(id);
    }

    public List<Following> getFollowersById(long id){
        return followingRepository.findByFollowing_IdEquals(id);
    }

    public boolean isFollowing(long idFollower, long idFollowing){
        return followingRepository.existsByFollower_IdEqualsAndFollowing_IdEquals(idFollower, idFollowing);
    }

    @Transactional
    public void deleteFollowing(long idFollower, long idFollowing){
        followingRepository.deleteByFollower_IdEqualsAndFollowing_IdEquals(idFollower, idFollowing);
    }

    @Transactional
    public void saveFollowing(Following following){
        followingRepository.save(following);
    }
}
