package com.KTUgrammeriai.KTUgram_backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;

    public User findByPersonId(long personId){
        return userRepository.findByPerson_Id(personId);
    }
}
