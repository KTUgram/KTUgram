package com.KTUgrammeriai.KTUgram_backend.likedPosts;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LikedPostRepository extends CrudRepository<LikedPost, Long> {
    List<LikedPost> findByUser_IdEquals(long id);
    Optional<LikedPost> findByPost_IdAndUser_Id(long id, long id1);

}
