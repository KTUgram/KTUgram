package com.KTUgrammeriai.KTUgram_backend.utils;

import com.KTUgrammeriai.KTUgram_backend.person.PersonDTO;
import com.KTUgrammeriai.KTUgram_backend.post.Post;
import com.KTUgrammeriai.KTUgram_backend.post.PostDTO;
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
        personDTO.setLast_active_date(user.getPerson().getLast_active_date());
        personDTO.setLast_active_time(user.getPerson().getLast_active_time());
        personDTO.setRegister_date(user.getPerson().getRegister_date());
        personDTO.setPhone_nr(user.getPerson().getPhone_nr());
        userDTO.setPerson(personDTO);
        userDTO.setId(user.getId());
        userDTO.setAbout(user.getAbout());
        userDTO.setProfile_pic(user.getProfile_pic());
        userDTO.setStatus(user.getStatus());
        userDTO.setState(user.getState());
        userDTO.setBirthday(user.getBirthday());
        userDTO.setCountry(user.getCountry());
        userDTO.setCity(user.getCity());

        return userDTO;
    }

    public static PostDTO postToPostDTO(Post post){
        PostDTO postDTO = new PostDTO();
        postDTO.setContent(post.getContent());
        postDTO.setTitle(post.getTitle());
        postDTO.setLocation(post.getLocation());
        postDTO.setTime(post.getTime());
        postDTO.setDate(post.getDate());
        postDTO.setState(post.getState());
        postDTO.setAbout(post.getAbout());
        postDTO.setId(post.getId());
        postDTO.setUser(userToUserDTO(post.getUser()));
        return postDTO;
    }

}