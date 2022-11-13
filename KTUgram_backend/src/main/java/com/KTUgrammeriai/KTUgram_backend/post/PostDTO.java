package com.KTUgrammeriai.KTUgram_backend.post;
import com.KTUgrammeriai.KTUgram_backend.user.User;
import com.KTUgrammeriai.KTUgram_backend.user.UserDTO;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class PostDTO {
    private long id;
    private Date date;
    private Time time;
    private String about;
    private String content;
    private String location;
    private String title;
    private String state;
    private UserDTO user;
}