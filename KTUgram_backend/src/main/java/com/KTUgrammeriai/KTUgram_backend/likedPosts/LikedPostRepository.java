package com.KTUgrammeriai.KTUgram_backend.likedPosts;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LikedPostRepository extends CrudRepository<LikedPost, Long> {
    List<LikedPost> findByUser_IdEquals(long id);

}
