package com.KTUgrammeriai.KTUgram_backend.post;

import com.KTUgrammeriai.KTUgram_backend.user.User;
import com.KTUgrammeriai.KTUgram_backend.user.UserDTO;
import com.KTUgrammeriai.KTUgram_backend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping(value = "/posts/get-posts")
    public ResponseEntity<List<PostDTO>> allUsers() {
        List<PostDTO> postsDTO = new ArrayList<>();
        List<Post> posts = postService.getAllPosts();

        for (Post post : posts){
            UserDTO userDTO = Utils.userToUserDTO(post.getUser());
            PostDTO postDTO = new PostDTO();
            postDTO.setUser(userDTO);
            postDTO.setId(post.getId());
            postDTO.setDate(post.getDate());
            postDTO.setTime(post.getTime());
            postDTO.setAbout(post.getAbout());
            postDTO.setContent(post.getContent());
            postDTO.setLocation(post.getLocation());
            postDTO.setTitle(post.getTitle());
            postDTO.setState(post.getState());
            postsDTO.add(postDTO);
        }

        return new ResponseEntity<>(postsDTO, HttpStatus.OK);
    }
}
