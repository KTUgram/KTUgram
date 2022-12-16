package com.KTUgrammeriai.KTUgram_backend.following;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FollowingRepository extends CrudRepository<Following, Long> {
    List<Following> findByFollower_IdEquals(long id);

    List<Following> findByFollowing_IdEquals(long id);

    boolean existsByFollower_IdEqualsAndFollowing_IdEquals(long id, long id1);

    long deleteByFollower_IdEqualsAndFollowing_IdEquals(long id, long id1);

}
