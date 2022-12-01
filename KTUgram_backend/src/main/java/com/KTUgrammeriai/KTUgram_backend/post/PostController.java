package com.KTUgrammeriai.KTUgram_backend.post;

import com.KTUgrammeriai.KTUgram_backend.CurrentUserImpl;
import com.KTUgrammeriai.KTUgram_backend.likedPosts.LikedPost;
import com.KTUgrammeriai.KTUgram_backend.likedPosts.LikedPostDTO;
import com.KTUgrammeriai.KTUgram_backend.likedPosts.LikedPostService;
import com.KTUgrammeriai.KTUgram_backend.user.User;
import com.KTUgrammeriai.KTUgram_backend.user.UserDTO;
import com.KTUgrammeriai.KTUgram_backend.user.UserService;
import com.KTUgrammeriai.KTUgram_backend.utils.FileUploadUtils;
import com.KTUgrammeriai.KTUgram_backend.utils.Utils;
import net.bytebuddy.utility.RandomString;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.html.HTMLInputElement;
import org.w3c.dom.html.HTMLTextAreaElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @Autowired
    LikedPostService likedPostService;

    @PostMapping(value = "/posts/get-posts")
    public ResponseEntity<List<PostDTO>> allPosts() {
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

    @PostMapping(value = "/posts/upload")
    public ResponseEntity<Void> uploadPost(@RequestParam("image")MultipartFile image, @RequestParam("title") String title, @RequestParam("about") String about) throws IOException {
        String extension = image.getOriginalFilename().split("\\.")[1];
        if(extension != null){
            long userId = CurrentUserImpl.getId();
            String fileName = RandomString.make(20) + "." + image.getOriginalFilename().split("\\.")[1];
            String uploadDir = "images/" + userId;
            Post post = new Post();
            post.setContent(uploadDir + "/" + fileName);
            post.setTitle(title);
            post.setAbout(about);
            post.setState("1");
            post.setUser(userService.findByPersonId(userId));
            FileUploadUtils.saveFile(uploadDir, fileName, image);
            postService.postRepository.save(post);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/posts/liked")
    public ResponseEntity<List<LikedPostDTO>> getLikedPosts(){
        User user = userService.findByPersonId(CurrentUserImpl.getId());
        List<LikedPost> likedPosts = likedPostService.getUserLikedPosts(user.getId());
        List<LikedPostDTO> likedPostsDTO = new ArrayList<>();

        for(LikedPost post : likedPosts){
            LikedPostDTO postDTO = new LikedPostDTO();
            postDTO.setId(post.getId());
            postDTO.setPost(Utils.postToPostDTO(post.getPost()));
            postDTO.setDate(post.getDate());
            postDTO.setTime(post.getTime());
            postDTO.setUser(Utils.userToUserDTO(post.getUser()));
            likedPostsDTO.add(postDTO);
        }
        return new ResponseEntity<>(likedPostsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/posts/like/{id}")
    public ResponseEntity<Void> likePost(@PathVariable("id") long postId){
        long userId = CurrentUserImpl.getId();
        User user = userService.findByPersonId(userId);
        Optional<Post> _likedPost = postService.getPostById(postId);
        if(_likedPost.isPresent()){
            Post likedPost = _likedPost.get();
            LikedPost newLikedPost = new LikedPost();
            newLikedPost.setUser(user);
            newLikedPost.setPost(likedPost);
            likedPostService.likedPostRepository.save(newLikedPost);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
