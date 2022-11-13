package com.KTUgrammeriai.KTUgram_backend.user;

import com.KTUgrammeriai.KTUgram_backend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(path = "/user/getUserById/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable long id){
        Optional<User> user_opt = userService.getById(id);
        if(user_opt.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = user_opt.get();
        UserDTO userDTO = Utils.userToUserDTO(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
