package com.KTUgrammeriai.KTUgram_backend.commentReports;

import com.KTUgrammeriai.KTUgram_backend.comments.CommentDTO;
import com.KTUgrammeriai.KTUgram_backend.post.PostDTO;
import com.KTUgrammeriai.KTUgram_backend.user.UserDTO;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class CommentReportDTO {
    private long id;
    private int reason;
    private String reasonComment;
    private Date date;
    private Time time;
    private CommentDTO comment;
    private UserDTO user;
}
