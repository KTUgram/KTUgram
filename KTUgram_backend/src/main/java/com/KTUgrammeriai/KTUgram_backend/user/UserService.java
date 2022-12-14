package com.KTUgrammeriai.KTUgram_backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;

    public User findByPersonId(long personId){
        return userRepository.findByPerson_Id(personId);
    }

    public List<User> getAllUsers(){
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> getById(long id){
       return userRepository.findById(id);
    }

    public boolean userExists(String username){
        return userRepository.existsByPerson_UsernameEquals(username);
    }
}
