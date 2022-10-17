package com.KTUgrammeriai.KTUgram_backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;

    public String getPassword(int id){
       Optional<User> user = userRepository.findById(id);
       if(user.isEmpty()){
           return null;
       }
       return user.get().getPassword();
    }
}
