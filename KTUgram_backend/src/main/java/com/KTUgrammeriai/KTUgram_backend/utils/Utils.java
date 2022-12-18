package com.KTUgrammeriai.KTUgram_backend.utils;

import com.KTUgrammeriai.KTUgram_backend.commentReports.CommentReport;
import com.KTUgrammeriai.KTUgram_backend.commentReports.CommentReportDTO;
import com.KTUgrammeriai.KTUgram_backend.blockedUsers.BlockedUsers;
import com.KTUgrammeriai.KTUgram_backend.blockedUsers.BlockedUsersDTO;
import com.KTUgrammeriai.KTUgram_backend.comments.Comment;
import com.KTUgrammeriai.KTUgram_backend.comments.CommentDTO;
import com.KTUgrammeriai.KTUgram_backend.messages.Message;
import com.KTUgrammeriai.KTUgram_backend.messages.MessageDTO;
import com.KTUgrammeriai.KTUgram_backend.person.Person;
import com.KTUgrammeriai.KTUgram_backend.person.PersonDTO;
import com.KTUgrammeriai.KTUgram_backend.post.Post;
import com.KTUgrammeriai.KTUgram_backend.post.PostDTO;
import com.KTUgrammeriai.KTUgram_backend.user.User;
import com.KTUgrammeriai.KTUgram_backend.user.UserDTO;
import com.KTUgrammeriai.KTUgram_backend.userReports.UserReport;
import com.KTUgrammeriai.KTUgram_backend.userReports.UserReportDTO;

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
        user.setConfirm(userDTO.getConfirm_nr());
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
        userDTO.setConfirm_nr(user.getConfirm());
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

    public static CommentReport convertCommentReport(CommentReportDTO reportDTO)
    {
        CommentReport report = new CommentReport();
        report.setComment(convertComment(reportDTO.getComment()));
        report.setDate(reportDTO.getDate());
        report.setReasonComment(reportDTO.getReasonComment());
        report.setTime(reportDTO.getTime());
        report.setUser(convertUser(reportDTO.getUser()));
        report.setId(reportDTO.getId());

        return report;
    }
    public static CommentReportDTO convertCommentReport(CommentReport report)
    {
        CommentReportDTO reportDTO = new CommentReportDTO();
        reportDTO.setComment(convertComment(report.getComment()));
        reportDTO.setTime(report.getTime());
        reportDTO.setDate(report.getDate());
        reportDTO.setUser(convertUser(report.getUser()));
        reportDTO.setId((report.getId()));
        reportDTO.setReasonComment(report.getReasonComment());

        return reportDTO;
    }

    public static UserReport convertUserReport(UserReportDTO reportDTO)
    {
        UserReport report = new UserReport();
        report.setReporter(convertUser(reportDTO.getReporter()));
        report.setReportedUser(convertUser(reportDTO.getReportedUser()));
        report.setDate(reportDTO.getDate());
        report.setTime(reportDTO.getTime());
        report.setId(reportDTO.getId());
        report.setReasonComment(reportDTO.getReasonComment());

        return report;
    }

    public static UserReportDTO convertUserReport(UserReport report)
    {
        UserReportDTO reportDTO = new UserReportDTO();
        reportDTO.setReporter(convertUser(report.getReporter()));
        reportDTO.setReportedUser(convertUser(report.getReportedUser()));
        reportDTO.setDate(report.getDate());
        reportDTO.setTime(report.getTime());
        reportDTO.setId(report.getId());
        reportDTO.setReasonComment(report.getReasonComment());

        return reportDTO;
    }

    public static Message convertMessage(MessageDTO messageDTO){
        Message message = new Message();
        message.setId(messageDTO.getId());
        message.setDate(messageDTO.getDate());
        message.setTime(messageDTO.getTime());
        message.setContent(messageDTO.getContent());
        message.setRead_date(messageDTO.getRead_date());
        message.setRead_time(messageDTO.getRead_time());
        message.setState(messageDTO.getState());
        message.setReceiver_user(convertUser(messageDTO.getReceiver_user()));
        message.setWriter_user(convertUser(messageDTO.getWriter_user()));

        return message;
    }

    public static BlockedUsers convertBlockedUsers(BlockedUsersDTO blockedUsersDTO){
        BlockedUsers blockedUsers = new BlockedUsers();
        blockedUsers.setId(blockedUsersDTO.getId());
        blockedUsers.setBlockerUser(convertUser(blockedUsersDTO.getBlockerUser()));
        blockedUsers.setBlockedUser(convertUser(blockedUsersDTO.getBlockedUser()));
        return blockedUsers;
    }
    public static BlockedUsersDTO convertBlockedUsers(BlockedUsers blockedUser){
        BlockedUsersDTO blockedUsersDTO = new BlockedUsersDTO();
        blockedUsersDTO.setId(blockedUser.getId());
        blockedUsersDTO.setBlockerUser(convertUser(blockedUser.getBlockerUser()));
        blockedUsersDTO.setBlockedUser(convertUser(blockedUser.getBlockedUser()));
        return blockedUsersDTO;
    }

    public static MessageDTO convertMessage(Message message){
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId(message.getId());
        messageDTO.setContent(message.getContent());
        messageDTO.setTime(message.getTime());
        messageDTO.setDate(message.getDate());
        messageDTO.setRead_date(message.getRead_date());
        messageDTO.setRead_time(message.getRead_time());
        messageDTO.setState(message.getState());
        messageDTO.setReceiver_user(convertUser(message.getReceiver_user()));
        messageDTO.setWriter_user(convertUser(message.getWriter_user()));

        return messageDTO;
    }

}
