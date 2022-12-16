package com.KTUgrammeriai.KTUgram_backend.following;

import com.KTUgrammeriai.KTUgram_backend.user.User;
import com.KTUgrammeriai.KTUgram_backend.user.UserDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;
import java.sql.Time;

@Data
public class FollowingDTO {
    private long id;
    private UserDTO following;
    private UserDTO follower;
    private Date date;
    private Time time;
}
