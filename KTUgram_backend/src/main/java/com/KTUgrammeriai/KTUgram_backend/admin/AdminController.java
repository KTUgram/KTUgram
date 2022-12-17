package com.KTUgrammeriai.KTUgram_backend.admin;

import com.KTUgrammeriai.KTUgram_backend.CurrentUserImpl;
import com.KTUgrammeriai.KTUgram_backend.admin.*;
import com.KTUgrammeriai.KTUgram_backend.commentReports.CommentReport;
import com.KTUgrammeriai.KTUgram_backend.commentReports.CommentReportDTO;
import com.KTUgrammeriai.KTUgram_backend.commentReports.CommentReportService;
import com.KTUgrammeriai.KTUgram_backend.comments.Comment;
import com.KTUgrammeriai.KTUgram_backend.comments.CommentDTO;
import com.KTUgrammeriai.KTUgram_backend.comments.CommentService;
import com.KTUgrammeriai.KTUgram_backend.post.PostDTO;
import com.KTUgrammeriai.KTUgram_backend.post.PostService;
import com.KTUgrammeriai.KTUgram_backend.post.Post;
import com.KTUgrammeriai.KTUgram_backend.user.User;
import com.KTUgrammeriai.KTUgram_backend.user.UserDTO;
import com.KTUgrammeriai.KTUgram_backend.user.UserRepository;
import com.KTUgrammeriai.KTUgram_backend.user.UserService;
import com.KTUgrammeriai.KTUgram_backend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Tuple;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.KTUgrammeriai.KTUgram_backend.utils.Utils.convertComment;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService users;

    @Autowired
    private CommentReportService commentReports;

    @Autowired
    private PostService posts;
    @Autowired
    private CommentService comments;

    @GetMapping(value = "/admin/reportsByCommentId/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CommentReportDTO>> getCommentReportsByCommentId(@PathVariable("id") long id){
        var reports = commentReports.commentReportRepository.findByComment_IdEquals(id);
        List<CommentReportDTO> commentsDTO = new ArrayList<>();
        for (CommentReport commentr : reports){
            commentsDTO.add(Utils.convertCommentReport(commentr));
        }
        return new ResponseEntity<>(commentsDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/admin/deleteCommentReports")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteCommentReports(@RequestBody int id){
        commentReports.commentReportRepository.deleteByComment_IdEquals(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping(value = "/admin/deleteCommentReportById")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteCommentReportById(@RequestBody int id){
        commentReports.commentReportRepository.deleteById((long)id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/admin/blockUserById")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> blockUserById(@RequestBody int id){
        Optional<User> user_opt = users.getById(id);
        if(user_opt.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = user_opt.get();
        user.setStatus(2);
        users.userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping(value = "/admin/unblockUserById")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> unblockUserById(@RequestBody int id){
        Optional<User> user_opt = users.getById(id);
        if(user_opt.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = user_opt.get();
        user.setStatus(1);
        users.userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping(value = "/admin/comments-by-user/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Pair<CommentDTO, Integer>>> getCommentsByUser(@PathVariable("id") long id){
        var comments = this.comments.commentRepository.findByUser_IdEquals(id);
        List<Pair<CommentDTO, Integer>> commentsDTO = new ArrayList<>();
        for (Comment comment : comments){
            var reports = this.commentReports.commentReportRepository.countByComment_IdEquals(comment.getId());
            commentsDTO.add(Pair.of(convertComment(comment), reports));
        }
        return new ResponseEntity<>(commentsDTO, HttpStatus.OK);
    }
    @GetMapping(value = "/admin/comments-by-user-sorted/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Pair<CommentDTO, Integer>>> getCommentsByUserSorted(@PathVariable("id") long id){
        var comments = this.comments.commentRepository.findByUser_IdEquals(id);
        List<Pair<CommentDTO, Integer>> commentsDTO = new ArrayList<>();
        for (Comment comment : comments){
            var reports = this.commentReports.commentReportRepository.countByComment_IdEquals(comment.getId());
            commentsDTO.add(Pair.of(convertComment(comment), reports));
        }
        commentsDTO.sort((final var f, final var s) -> s.getSecond().compareTo(f.getSecond()));
        return new ResponseEntity<>(commentsDTO, HttpStatus.OK);
    }
    @GetMapping(value = "/admin/commentsByAllUsers")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Pair<CommentDTO, Integer>>> getCommentsByAllUsers(){
        var comments = this.comments.commentRepository.findAll();
        List<Pair<CommentDTO, Integer>> commentsDTO = new ArrayList<>();
        for (Comment comment : comments){
            var reports = this.commentReports.commentReportRepository.countByComment_IdEquals(comment.getId());
            commentsDTO.add(Pair.of(convertComment(comment), reports));
        }
        return new ResponseEntity<>(commentsDTO, HttpStatus.OK);
    }
    @GetMapping(value = "/admin/commentsByAllUsersSortByReports")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Pair<CommentDTO, Integer>>> getCommentsByAllUsersSortedByReports(){
        var comments = this.comments.commentRepository.findAll();

        List<Pair<CommentDTO, Integer>> commentsDTO = new ArrayList<>();
        for (Comment comment : comments){
            var reports = this.commentReports.commentReportRepository.countByComment_IdEquals(comment.getId());
            commentsDTO.add(Pair.of(convertComment(comment), reports));
        }
        commentsDTO.sort((final var f, final var s) -> s.getSecond().compareTo(f.getSecond()));
        return new ResponseEntity<>(commentsDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/admin/deleteUserComment/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteUserComment(@RequestBody long id){
        var optComment = comments.commentRepository.findById(id);
        if(optComment.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var comment = optComment.get();
        comments.commentRepository.delete(comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/admin/deleteUserPost/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteUserPost(@RequestBody long id){
        var optPost = posts.getPostById(id);
        if(optPost.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var post = optPost.get();
        posts.postRepository.delete(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/admin/getUserPosts/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<PostDTO>> getUserPosts(@PathVariable("id") long id){
        var posts = this.posts.postRepository.findByUser_IdEquals(id);
        List<PostDTO> postsDTO = new ArrayList<>();
        for (Post post : posts){
            postsDTO.add(Utils.convertPost(post));
        }
        return new ResponseEntity<>(postsDTO, HttpStatus.OK);
    }

}