package com.KTUgrammeriai.KTUgram_backend.messages;

import com.KTUgrammeriai.KTUgram_backend.post.PostDTO;
import com.KTUgrammeriai.KTUgram_backend.user.UserDTO;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class MessageDTO {
    private long id;
    private String content;
    private Time time;
    private Date date;
    private Date read_date;
    private Time read_time;
    private int state;

    private UserDTO receiver_user;
    private UserDTO writer_user;
}
