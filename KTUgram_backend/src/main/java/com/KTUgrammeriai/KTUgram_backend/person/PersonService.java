package com.KTUgrammeriai.KTUgram_backend.person;

import com.KTUgrammeriai.KTUgram_backend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    public PersonRepository personRepository;

    public Person getPersonByUsername(String username){
        return personRepository.findPersonByUsername(username);
    }

    public String getPassword(long id){
        Person person = personRepository.findPersonById(id);
        if(person == null){
            return null;
        }
        return person.getPassword();
    }
}
