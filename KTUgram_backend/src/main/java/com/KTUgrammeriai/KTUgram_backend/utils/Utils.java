package com.KTUgrammeriai.KTUgram_backend.utils;

import com.KTUgrammeriai.KTUgram_backend.person.PersonDTO;
import com.KTUgrammeriai.KTUgram_backend.user.User;
import com.KTUgrammeriai.KTUgram_backend.user.UserDTO;

public class Utils {

    public static UserDTO userToUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(user.getPerson().getId());
        personDTO.setUsername(user.getPerson().getUsername());
        personDTO.setEmail(user.getPerson().getEmail());
        personDTO.setName(user.getPerson().getName());
        personDTO.setSurname(user.getPerson().getSurname());
        userDTO.setPerson(personDTO);
        userDTO.setId(user.getId());
        userDTO.setAbout(user.getAbout());
        userDTO.setProfile_pic(user.getProfile_pic());

        return userDTO;
    }

}
