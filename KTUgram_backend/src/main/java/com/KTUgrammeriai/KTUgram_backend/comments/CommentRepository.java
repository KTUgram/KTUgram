package com.KTUgrammeriai.KTUgram_backend.comments;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findByPost_IdEquals(long id);
    List<Comment> findByPost_IdEqualsAndUser_IdEquals(long id, long id1);

    List<Comment> findByPost_IdEqualsAndUser_IdIsNot(long id, long id1);

    List<Comment> findByUser_IdEquals(long id);

}
