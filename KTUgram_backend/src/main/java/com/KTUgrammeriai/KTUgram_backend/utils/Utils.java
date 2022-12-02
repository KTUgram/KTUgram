package com.KTUgrammeriai.KTUgram_backend.utils;

import com.KTUgrammeriai.KTUgram_backend.comments.Comment;
import com.KTUgrammeriai.KTUgram_backend.comments.CommentDTO;
import com.KTUgrammeriai.KTUgram_backend.person.Person;
import com.KTUgrammeriai.KTUgram_backend.person.PersonDTO;
import com.KTUgrammeriai.KTUgram_backend.post.Post;
import com.KTUgrammeriai.KTUgram_backend.post.PostDTO;
import com.KTUgrammeriai.KTUgram_backend.user.User;
import com.KTUgrammeriai.KTUgram_backend.user.UserDTO;

public class Utils {

    public static PersonDTO convertPerson(Person person){
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setUsername(person.getUsername());
        personDTO.setEmail(person.getEmail());
        personDTO.setName(person.getName());
        personDTO.setSurname(person.getSurname());
        personDTO.setLast_active_date(person.getLast_active_date());
        personDTO.setLast_active_time(person.getLast_active_time());
        personDTO.setRegister_date(person.getRegister_date());
        personDTO.setPhone_nr(person.getPhone_nr());

        return personDTO;
    }

    public static Person convertPerson(PersonDTO personDTO){
        Person person = new Person();
        person.setId(personDTO.getId());
        person.setUsername(personDTO.getUsername());
        person.setEmail(personDTO.getEmail());
        person.setName(personDTO.getName());
        person.setSurname(personDTO.getSurname());
        person.setLast_active_date(personDTO.getLast_active_date());
        person.setLast_active_time(personDTO.getLast_active_time());
        person.setRegister_date(personDTO.getRegister_date());
        person.setPhone_nr(personDTO.getPhone_nr());

        return person;
    }

    public static User convertUser(UserDTO userDTO){
        User user = new User();
        user.setPerson(convertPerson(userDTO.getPerson()));
        user.setId(userDTO.getId());
        user.setAbout(userDTO.getAbout());
        user.setProfile_pic(userDTO.getProfile_pic());
        user.setStatus(userDTO.getStatus());
        user.setState(userDTO.getState());
        user.setBirthday(userDTO.getBirthday());
        user.setCountry(userDTO.getCountry());
        user.setCity(userDTO.getCity());

        return user;
    }

    public static UserDTO convertUser(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setPerson(convertPerson(user.getPerson()));
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

    public static Post convertPost(PostDTO postDTO){
        Post post = new Post();
        post.setContent(postDTO.getContent());
        post.setTitle(postDTO.getTitle());
        post.setLocation(postDTO.getLocation());
        post.setTime(postDTO.getTime());
        post.setDate(postDTO.getDate());
        post.setState(postDTO.getState());
        post.setAbout(postDTO.getAbout());
        post.setId(postDTO.getId());
        post.setUser(convertUser(postDTO.getUser()));
        return post;
    }

    public static PostDTO convertPost(Post post){
        PostDTO postDTO = new PostDTO();
        postDTO.setContent(post.getContent());
        postDTO.setTitle(post.getTitle());
        postDTO.setLocation(post.getLocation());
        postDTO.setTime(post.getTime());
        postDTO.setDate(post.getDate());
        postDTO.setState(post.getState());
        postDTO.setAbout(post.getAbout());
        postDTO.setId(post.getId());
        postDTO.setUser(convertUser(post.getUser()));
        return postDTO;
    }

    public static CommentDTO convertComment(Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setContent(comment.getContent());
        commentDTO.setDate(comment.getDate());
        commentDTO.setTime(comment.getTime());
        commentDTO.setUser(convertUser(comment.getUser()));
        commentDTO.setPost(convertPost(comment.getPost()));
        return commentDTO;
    }

    public static Comment convertComment(CommentDTO commentDTO){
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setContent(commentDTO.getContent());
        comment.setDate(commentDTO.getDate());
        comment.setTime(commentDTO.getTime());
        comment.setUser(convertUser(commentDTO.getUser()));
        comment.setPost(convertPost(commentDTO.getPost()));
        return comment;
    }

}
