package com.KTUgrammeriai.KTUgram_backend.post;

import com.KTUgrammeriai.KTUgram_backend.CurrentUserImpl;
import com.KTUgrammeriai.KTUgram_backend.commentReports.CommentReport;
import com.KTUgrammeriai.KTUgram_backend.commentReports.CommentReportDTO;
import com.KTUgrammeriai.KTUgram_backend.commentReports.CommentReportRepository;
import com.KTUgrammeriai.KTUgram_backend.commentReports.CommentReportService;
import com.KTUgrammeriai.KTUgram_backend.comments.Comment;
import com.KTUgrammeriai.KTUgram_backend.comments.CommentDTO;
import com.KTUgrammeriai.KTUgram_backend.comments.CommentService;
import com.KTUgrammeriai.KTUgram_backend.likedPosts.LikedPost;
import com.KTUgrammeriai.KTUgram_backend.likedPosts.LikedPostDTO;
import com.KTUgrammeriai.KTUgram_backend.likedPosts.LikedPostService;
import com.KTUgrammeriai.KTUgram_backend.postReports.PostReport;
import com.KTUgrammeriai.KTUgram_backend.postReports.PostReportService;
import com.KTUgrammeriai.KTUgram_backend.user.User;
import com.KTUgrammeriai.KTUgram_backend.user.UserDTO;
import com.KTUgrammeriai.KTUgram_backend.user.UserService;
import com.KTUgrammeriai.KTUgram_backend.userReports.UserReport;
import com.KTUgrammeriai.KTUgram_backend.userReports.UserReportDTO;
import com.KTUgrammeriai.KTUgram_backend.userReports.UserReportService;
import com.KTUgrammeriai.KTUgram_backend.utils.FileUploadUtils;
import com.KTUgrammeriai.KTUgram_backend.utils.Utils;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
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

    @Autowired
    CommentService commentService;
    
    @Autowired
    CommentReportService commentReportservice;

    @Autowired
    UserReportService userReportservice;

    @Autowired
    PostReportService postReportService;

    @PostMapping(value = "/posts/get-posts")
    public ResponseEntity<List<PostDTO>> allPosts() {
        List<PostDTO> postsDTO = new ArrayList<>();
        List<Post> posts = postService.getAllPosts();

        for (Post post : posts){
            UserDTO userDTO = Utils.convertUser(post.getUser());
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
            postDTO.setPost(Utils.convertPost(post.getPost()));
            postDTO.setDate(post.getDate());
            postDTO.setTime(post.getTime());
            postDTO.setUser(Utils.convertUser(post.getUser()));
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
            Optional<LikedPost> likedPostOpt = likedPostService.findUserLikedPost(user.getId(), postId);

            if(likedPostOpt.isEmpty()){
                LikedPost newLikedPost = new LikedPost();
                newLikedPost.setUser(user);
                newLikedPost.setPost(likedPost);
                likedPostService.save(newLikedPost);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            System.out.println("liked");
            likedPostService.delete(likedPostOpt.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/posts/comments/{id}")
    public ResponseEntity<List<CommentDTO>> getComments(@PathVariable("id") long id){
        List<Comment> comments = commentService.getPostComments(id);
        List<CommentDTO> commentsDTO = new ArrayList<>();

        for (Comment comment : comments){
            commentsDTO.add(Utils.convertComment(comment));
        }

        return new ResponseEntity<>(commentsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/posts/comments-by-user/{id}")
    public ResponseEntity<List<CommentDTO>> getCommentsByUser(@PathVariable("id") long id){
        User user = userService.findByPersonId(CurrentUserImpl.getId());
        List<Comment> comments = commentService.getPostCommentsByUser(id, user.getId());
        List<CommentDTO> commentsDTO = new ArrayList<>();

        for (Comment comment : comments){
            commentsDTO.add(Utils.convertComment(comment));
        }

        return new ResponseEntity<>(commentsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/posts/comments-except-user/{id}")
    public ResponseEntity<List<CommentDTO>> getCommentsExceptUser(@PathVariable("id") long id){
        User user = userService.findByPersonId(CurrentUserImpl.getId());
        List<Comment> comments = commentService.getPostCommentsExceptUser(id, user.getId());
        List<CommentDTO> commentsDTO = new ArrayList<>();

        for (Comment comment : comments){
            commentsDTO.add(Utils.convertComment(comment));
        }

        return new ResponseEntity<>(commentsDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/posts/add-comment")
    public ResponseEntity<Void> addComment(@RequestBody CommentDTO commentDTO){
        User user = userService.findByPersonId(CurrentUserImpl.getId());
        commentDTO.setUser(Utils.convertUser(user));
        System.out.println(commentDTO.getPost().getContent());
        Comment comment = Utils.convertComment(commentDTO);
        commentService.commentRepository.save(comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/posts/edit-comment")
    public ResponseEntity<Void> editComment(@RequestBody CommentDTO commentDTO){
        Comment comment = Utils.convertComment(commentDTO);
        commentService.commentRepository.save(comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/posts/delete-comment")
    public ResponseEntity<Void> deleteComment(@RequestBody CommentDTO commentDTO){
        User user = userService.findByPersonId(CurrentUserImpl.getId());

        if(commentDTO.getUser().getId() != user.getId()){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Comment comment = Utils.convertComment(commentDTO);
        commentService.commentRepository.delete(comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/posts/delete-post/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable(name = "id") long id){
        User user = userService.findByPersonId(CurrentUserImpl.getId());

        if(!postService.isPostByUser(id, user.getId())){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Post post = postService.getPostById(id).get();
        post.setState("2");
        postService.postRepository.save(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/posts/report-comment/{id}")
    public ResponseEntity<Void> reportComment(@PathVariable("id") long id, @RequestBody String comment){
        User user = userService.findByPersonId(CurrentUserImpl.getId());

        CommentReport commentReport = new CommentReport();
        commentReport.setComment(commentService.getCommentById(id).get());
        commentReport.setUser(user);
        commentReport.setReasonComment(comment);
        commentReportservice.save(commentReport);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/posts/report-user/{id}")
    public ResponseEntity<Void> reportUser(@PathVariable("id") long id, @RequestBody String comment){
        User user = userService.findByPersonId(CurrentUserImpl.getId());

        UserReport userReport = new UserReport();
        userReport.setReportedUser(userService.getById(id).get());
        userReport.setReporter(user);
        userReport.setReasonComment(comment);
        userReportservice.userReportRepository.save(userReport);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/posts/report-post/{id}")
    public ResponseEntity<Void> reportPost(@PathVariable("id") long id, @RequestBody String comment){
        User user = userService.findByPersonId(CurrentUserImpl.getId());

        PostReport postReport = new PostReport();
        postReport.setPost(postService.getPostById(id).get());
        postReport.setUser(user);
        postReport.setReasonComment(comment);
        postReportService.save(postReport);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/posts/get-post/{id}")
    public ResponseEntity<PostDTO> getPost(@PathVariable("id") long id){
        Optional<Post> post = postService.getPostById(id);
        if(post.isPresent()){
            PostDTO postDTO = Utils.convertPost(post.get());
            return new ResponseEntity<>(postDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/posts/get-posts-by-user/{id}")
    public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable("id") long id){
        List<Post> userPosts = postService.getPostsByUser(id);
        List<PostDTO> userPostsDTO = new ArrayList<>();

        for(Post post : userPosts){
            userPostsDTO.add(Utils.convertPost(post));
        }
        return new ResponseEntity<>(userPostsDTO, HttpStatus.OK);
    }
    
}
