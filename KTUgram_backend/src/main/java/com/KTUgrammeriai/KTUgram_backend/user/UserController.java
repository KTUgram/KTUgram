package com.KTUgrammeriai.KTUgram_backend.user;

import com.KTUgrammeriai.KTUgram_backend.CurrentUserImpl;
import com.KTUgrammeriai.KTUgram_backend.following.Following;
import com.KTUgrammeriai.KTUgram_backend.following.FollowingService;
import com.KTUgrammeriai.KTUgram_backend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private FollowingService followingService;

    @RequestMapping(path = "/user/getUserById/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable long id){
        Optional<User> user_opt = userService.getById(id);
        if(user_opt.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = user_opt.get();
        UserDTO userDTO = Utils.convertUser(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/user/followers/{id}")
    public ResponseEntity<List<UserDTO>> getFollowers(@PathVariable("id") long id){
        List<Following> followers = followingService.getFollowersById(id);
        List<UserDTO> followersUsers = new ArrayList<>();
        followers.forEach(fol -> followersUsers.add(Utils.convertUser(fol.getFollower())));
        return new ResponseEntity<>(followersUsers, HttpStatus.OK);
    }

    @GetMapping(path = "/user/following/{id}")
    public ResponseEntity<List<UserDTO>> GetFollowing(@PathVariable("id") long id){
        List<Following> followers = followingService.getFollowingById(id);
        List<UserDTO> users = new ArrayList<>();
        followers.forEach(fol -> users.add(Utils.convertUser(fol.getFollower())));
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(path = "/user/following")
    public ResponseEntity<List<UserDTO>> GetFollowing(){
        List<Following> followers = followingService.getFollowingById(userService.findByPersonId(CurrentUserImpl.getId()).getId());
        List<UserDTO> users = new ArrayList<>();
        followers.forEach(fol -> users.add(Utils.convertUser(fol.getFollowing())));
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(path = "/user/update-follow/{id}")
    public ResponseEntity<Void> updateFollow(@PathVariable("id") long id){
        User user = userService.findByPersonId(CurrentUserImpl.getId());
        if(followingService.isFollowing(user.getId(), id)){
            followingService.deleteFollowing(user.getId(), id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        Optional<User> userToFollow = userService.userRepository.findById(id);
        if(userToFollow.isPresent()){
            Following newFollow = new Following();
            newFollow.setFollower(user);
            newFollow.setFollowing(userToFollow.get());
            followingService.saveFollowing(newFollow);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/user/is-following/{id}")
    public ResponseEntity<Boolean> isFollowing(@PathVariable("id") long id){
        return new ResponseEntity<>(followingService.isFollowing(userService.findByPersonId(CurrentUserImpl.getId()).getId(), id), HttpStatus.OK);
    }
}
