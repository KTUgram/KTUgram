package com.KTUgrammeriai.KTUgram_backend.messages;

import com.KTUgrammeriai.KTUgram_backend.CurrentUserImpl;
import com.KTUgrammeriai.KTUgram_backend.blockedUsers.BlockedUsers;
import com.KTUgrammeriai.KTUgram_backend.blockedUsers.BlockedUsersDTO;
import com.KTUgrammeriai.KTUgram_backend.blockedUsers.BlockedUsersRepository;
import com.KTUgrammeriai.KTUgram_backend.blockedUsers.BlockedUsersService;
import com.KTUgrammeriai.KTUgram_backend.comments.Comment;
import com.KTUgrammeriai.KTUgram_backend.comments.CommentDTO;
import com.KTUgrammeriai.KTUgram_backend.comments.CommentService;
import com.KTUgrammeriai.KTUgram_backend.email.EmailDetails;
import com.KTUgrammeriai.KTUgram_backend.email.EmailService;
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

    @Autowired
    BlockedUsersService blockedUsersService;

    PersonService personService;

    @Autowired
    UserService userService;

    @Autowired
    LikedPostService likedPostService;

    @Autowired
    CommentService commentService;

    @Autowired
    EmailService emailService;

    @GetMapping(value = "/messages/get-users")
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> usersDTO = new ArrayList<>();
        List<User> users = userService.getAllUsers();
        List<BlockedUsers> blockedUsers = blockedUsersService.getAllBlockedUsers();
        User loggedUser = userService.findByPersonId(CurrentUserImpl.getId());
        for (User user: users) {
            UserDTO singleUser = Utils.convertUser(user);
            boolean blocked = false;
            for(BlockedUsers blockedUser: blockedUsers){
                if(blockedUser.getBlockerUser().getId() == loggedUser.getId() && blockedUser.getBlockedUser().getId() == singleUser.getId()){
                    blocked = true;
                    break;
                }
            }
            if(loggedUser.getId() != singleUser.getId() && singleUser.getStatus() != 2)
            {
                if(!blocked) {
                    usersDTO.add(Utils.convertUser(user));
                }
            }
        }

        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/messages/get-blocked-users")
    public ResponseEntity<List<BlockedUsersDTO>> getBlockedUsers() {
        List<BlockedUsersDTO> blockedUsersDTO = new ArrayList<>();
        List<BlockedUsers> blockedUsers = blockedUsersService.getAllBlockedUsers();
        User loggedUser = userService.findByPersonId(CurrentUserImpl.getId());
        for (BlockedUsers blockedUser: blockedUsers) {
            if (blockedUser.getBlockerUser().getId() == loggedUser.getId()){
                blockedUsersDTO.add(Utils.convertBlockedUsers(blockedUser));
            }
        }

        return new ResponseEntity<>(blockedUsersDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/messages/get-user/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") long userId) {
        Optional<User> user_opt = userService.getById(userId);
        if(user_opt.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = user_opt.get();
        UserDTO userDTO = Utils.convertUser(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/messages/get-logged-user")
    public ResponseEntity<UserDTO> getLoggedUser() {
        User loggedUser = userService.findByPersonId(CurrentUserImpl.getId());
        UserDTO user = Utils.convertUser(loggedUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PostMapping(value = "/messages/add-message/{id}")
    public ResponseEntity<Void> addMessage(@PathVariable("id") long receiverId, @RequestBody MessageDTO messageDTO){
        User loggedUser = userService.findByPersonId(CurrentUserImpl.getId());
        UserDTO loggedUserDTO = Utils.convertUser(loggedUser);
        Optional<User> user_opt = userService.getById(receiverId);
        if(user_opt.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User receiverUser = user_opt.get();
        UserDTO receiverUserDTO = Utils.convertUser(receiverUser);

        // setting values of users
        messageDTO.setReceiver_user(receiverUserDTO);
        messageDTO.setWriter_user(loggedUserDTO);
        messageDTO.setState(1);
        UserDTO reader = messageDTO.getReceiver_user();
        if(reader == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Message message = Utils.convertMessage(messageDTO);
        messageService.MessageRepository.save(message);

        EmailDetails email = new EmailDetails();
        email.setRecipient(receiverUser.getPerson().getEmail());
        email.setSubject("KTUGram messaging");
        email.setMsgBody(String.format("You got a message from &s", loggedUser.getPerson().getUsername()));
        emailService.sendSimpleMail(email);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/messages/block-user-messages/{id}")
    public ResponseEntity<Void> blockUserMessages(@PathVariable("id") long otherUserId){
        User loggedUser = userService.findByPersonId(CurrentUserImpl.getId());
        UserDTO loggedUserDTO = Utils.convertUser(loggedUser);
        Optional<User> user_opt = userService.getById(otherUserId);
        if(user_opt.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User otherUser = user_opt.get();
        UserDTO otherUserDTO = Utils.convertUser(otherUser);
        BlockedUsersDTO blockedUsersDTO = new BlockedUsersDTO();
        blockedUsersDTO.setBlockerUser(loggedUserDTO);
        blockedUsersDTO.setBlockedUser(otherUserDTO);
        BlockedUsers blockedUsers = Utils.convertBlockedUsers(blockedUsersDTO);

        blockedUsersService.saveBlockedUsers(blockedUsers);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/messages/edit-message")
    public ResponseEntity<Void> editMessage(@RequestBody MessageDTO messageDTO){
        Message message = Utils.convertMessage(messageDTO);
        messageService.MessageRepository.save(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/messages/delete-message")
    public ResponseEntity<Void> deleteComment(@RequestBody MessageDTO messageDTO){
        User user = userService.findByPersonId(CurrentUserImpl.getId());

        if(messageDTO.getWriter_user().getId() != user.getId()){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Message message = Utils.convertMessage(messageDTO);
        messageService.MessageRepository.delete(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/messages/remove-blocked-user/{id}")
    public ResponseEntity<Void> removeBlockedUser(@PathVariable("id") long blockedDataId){

        User user = userService.findByPersonId(CurrentUserImpl.getId());
        List<BlockedUsers> blockedUsers = blockedUsersService.getAllBlockedUsers();
        System.out.println(blockedDataId);
        for(BlockedUsers blockedUser: blockedUsers){
            if(blockedUser.getId() == blockedDataId){
                if(blockedUser.getBlockerUser().getId() == user.getId()) {
                    blockedUsersService.deleteBlockedUser(blockedUser);
                    return new ResponseEntity<>(HttpStatus.OK);
                }
                else{
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
