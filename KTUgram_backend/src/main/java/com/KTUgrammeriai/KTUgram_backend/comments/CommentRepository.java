package com.KTUgrammeriai.KTUgram_backend.comments;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findByPost_IdEquals(long id);


}
