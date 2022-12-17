package com.KTUgrammeriai.KTUgram_backend.userReports;

import com.KTUgrammeriai.KTUgram_backend.post.PostDTO;
import com.KTUgrammeriai.KTUgram_backend.user.UserDTO;
import lombok.Data;
import java.time.LocalTime;
import java.util.Date;

@Data
public class UserReportDTO {
    private long id;
    private int reason;
    private String reasonComment;
    private Date date;
    private LocalTime time;
    private UserDTO reporter;
    private UserDTO reportedUser;
}
