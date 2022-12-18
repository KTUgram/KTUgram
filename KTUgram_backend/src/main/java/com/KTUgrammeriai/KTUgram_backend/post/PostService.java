package com.KTUgrammeriai.KTUgram_backend.post;

import com.KTUgrammeriai.KTUgram_backend.person.PersonDTO;
import com.KTUgrammeriai.KTUgram_backend.user.User;
import com.KTUgrammeriai.KTUgram_backend.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    public PostRepository postRepository;

    public List<Post> getAllPosts(){
        return postRepository.findByStateOrderByIdDesc("1");
    }

    public Optional<Post> getPostById(long id){
        return postRepository.findById(id);
    }

    public List<Post> getPostsByUser(long id){
        return postRepository.findByUser_IdAndStateOrderByIdDesc(id, "1");
    }

    public boolean isPostByUser(long postId, long userId){
        return postRepository.existsByIdAndUser_Id(postId, userId);
    }
}
