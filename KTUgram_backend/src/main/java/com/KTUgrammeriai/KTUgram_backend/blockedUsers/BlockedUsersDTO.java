package com.KTUgrammeriai.KTUgram_backend.blockedUsers;

import com.KTUgrammeriai.KTUgram_backend.user.UserDTO;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class BlockedUsersDTO {
    private long id;
    private UserDTO blockerUser;
    private UserDTO blockedUser;
    private Date date;
    private Time time;
}
