package com.KTUgrammeriai.KTUgram_backend.comments;

import com.KTUgrammeriai.KTUgram_backend.post.Post;
import com.KTUgrammeriai.KTUgram_backend.post.PostDTO;
import com.KTUgrammeriai.KTUgram_backend.user.User;
import com.KTUgrammeriai.KTUgram_backend.user.UserDTO;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class CommentDTO {
    private long id;
    private String content;
    private Time time;
    private Date date;
    private PostDTO post;
    private UserDTO user;
}
