package com.KTUgrammeriai.KTUgram_backend.messages;

import com.KTUgrammeriai.KTUgram_backend.CurrentUserImpl;
import com.KTUgrammeriai.KTUgram_backend.comments.Comment;
import com.KTUgrammeriai.KTUgram_backend.comments.CommentDTO;
import com.KTUgrammeriai.KTUgram_backend.comments.CommentService;
import com.KTUgrammeriai.KTUgram_backend.likedPosts.LikedPost;
import com.KTUgrammeriai.KTUgram_backend.likedPosts.LikedPostDTO;
import com.KTUgrammeriai.KTUgram_backend.likedPosts.LikedPostService;
import com.KTUgrammeriai.KTUgram_backend.person.PersonService;
import com.KTUgrammeriai.KTUgram_backend.post.Post;
import com.KTUgrammeriai.KTUgram_backend.post.PostDTO;
import com.KTUgrammeriai.KTUgram_backend.post.PostService;
import com.KTUgrammeriai.KTUgram_backend.user.User;
import com.KTUgrammeriai.KTUgram_backend.user.UserDTO;
import com.KTUgrammeriai.KTUgram_backend.user.UserService;
import com.KTUgrammeriai.KTUgram_backend.utils.FileUploadUtils;
import com.KTUgrammeriai.KTUgram_backend.utils.Utils;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.support.TaskUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class MessageController {

    @Autowired
    PostService postService;

    @Autowired
    MessageService messageService;

    PersonService personService;

    @Autowired
    UserService userService;

    @Autowired
    LikedPostService likedPostService;

    @Autowired
    CommentService commentService;

    @GetMapping(value = "/messages/get-users")
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> usersDTO = new ArrayList<>();
        List<User> users = userService.getAllUsers();
        User loggedUser = userService.findByPersonId(CurrentUserImpl.getId());
        for (User user: users) {
            UserDTO singleUser = Utils.convertUser(user);
            if(loggedUser.getId() != singleUser.getId())
            {
                usersDTO.add(Utils.convertUser(user));
            }
        }

        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/messages/get-user/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") long userId) {
        System.out.print("Ieskomas pagal ID:");
        System.out.println(userId);
        Optional<User> user_opt = userService.getById(userId);
        if(user_opt.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User user = user_opt.get();
        System.out.print("user_opt.get() rezultatas: ");
        System.out.println(user.getId());
        UserDTO userDTO = Utils.convertUser(user);
        System.out.print("UserDTO duomenys backende: ");
        System.out.println(userDTO.getId());
        System.out.println(userDTO.getState());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/messages/get-logged-user")
    public ResponseEntity<UserDTO> getLoggedUser() {
        User loggedUser = userService.findByPersonId(CurrentUserImpl.getId());
        UserDTO user = Utils.convertUser(loggedUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // smth wrong here messages/add-message
    @PostMapping(value = "/messages/add-message")
    public ResponseEntity<Void> addMessage(@RequestBody MessageDTO messageDTO){
        UserDTO reader = messageDTO.getReceiver_user();
        if(reader == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Message message = Utils.convertMessage(messageDTO);
        messageService.MessageRepository.save(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/messages/get-messages/{id}")
    public ResponseEntity<List<MessageDTO>> getMessages(@PathVariable("id") long otherUserId) {
        User loggedUser = userService.findByPersonId(CurrentUserImpl.getId());
        List<MessageDTO> messagesDTO = new ArrayList<>();
        List<Message> messages = messageService.getAllMessages();
        for (Message message: messages) {
            User writer = message.getWriter_user();
            User receiver = message.getReceiver_user();
            if((writer.getId() == loggedUser.getId() || writer.getId() == otherUserId) && (receiver.getId() == loggedUser.getId() || receiver.getId() == otherUserId)){
                messagesDTO.add(Utils.convertMessage(message));
            }
        }
        return new ResponseEntity<>(messagesDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/messages/messages-by-user/{id}")
    public ResponseEntity<List<MessageDTO>> getMessagesByUser(@PathVariable("id") long id){
        User loggedUser = userService.findByPersonId(CurrentUserImpl.getId());
        List<Message> messages = messageService.getAllMessages();
        List<MessageDTO> messagesDTO = new ArrayList<>();
        for (Message message: messages) {
            User writer = message.getWriter_user();
            User receiver = message.getReceiver_user();
            if(receiver.getId() == id && writer.getId() == loggedUser.getId()){
                messagesDTO.add(Utils.convertMessage(message));
            }
        }
        return new ResponseEntity<>(messagesDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/messages/messages-except-user/{id}")
    public ResponseEntity<List<MessageDTO>> getMessagesExceptUser(@PathVariable("id") long id){
        User loggedUser = userService.findByPersonId(CurrentUserImpl.getId());
        List<Message> messages = messageService.getAllMessages();
        List<MessageDTO> messagesDTO = new ArrayList<>();
        for (Message message: messages) {
            User writer = message.getWriter_user();
            User receiver = message.getReceiver_user();
            if(receiver.getId() == loggedUser.getId() && writer.getId() == id){
                messagesDTO.add(Utils.convertMessage(message));
            }
        }
        return new ResponseEntity<>(messagesDTO, HttpStatus.OK);
    }

}
