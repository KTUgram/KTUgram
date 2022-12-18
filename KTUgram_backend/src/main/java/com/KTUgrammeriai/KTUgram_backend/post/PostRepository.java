package com.KTUgrammeriai.KTUgram_backend.post;

import com.KTUgrammeriai.KTUgram_backend.comments.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    public List<Post> findByUser_IdEquals(long id);
    public List<Post> findByOrderByIdDesc();
    List<Post> findByStateIsOrderByDateDesc(String state);

    boolean existsByIdAndUser_Id(long id, long id1);

    List<Post> findByUser_IdAndStateOrderByIdDesc(long id, String state);
    List<Post> findByStateOrderByIdDesc(String state);




}
