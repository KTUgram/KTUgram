package com.KTUgrammeriai.KTUgram_backend.admin;

import com.KTUgrammeriai.KTUgram_backend.admin.*;
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
import java.util.List;
import java.util.Optional;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService users;

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
}