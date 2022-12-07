package com.KTUgrammeriai.KTUgram_backend.admin;

import com.KTUgrammeriai.KTUgram_backend.CurrentUserImpl;
import com.KTUgrammeriai.KTUgram_backend.admin.*;
import com.KTUgrammeriai.KTUgram_backend.comments.Comment;
import com.KTUgrammeriai.KTUgram_backend.comments.CommentDTO;
import com.KTUgrammeriai.KTUgram_backend.comments.CommentService;
import com.KTUgrammeriai.KTUgram_backend.user.User;
import com.KTUgrammeriai.KTUgram_backend.user.UserDTO;
import com.KTUgrammeriai.KTUgram_backend.user.UserRepository;
import com.KTUgrammeriai.KTUgram_backend.user.UserService;
import com.KTUgrammeriai.KTUgram_backend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService users;


    @Autowired
    private CommentService comments;

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
    public ResponseEntity<List<CommentDTO>> getCommentsByUser(@PathVariable("id") long id){
        var comments = this.comments.commentRepository.findByUser_IdEquals(id);
        List<CommentDTO> commentsDTO = new ArrayList<>();
        for (Comment comment : comments){
            commentsDTO.add(Utils.convertComment(comment));
        }
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

}