package com.KTUgrammeriai.KTUgram_backend.likedPosts;
import com.KTUgrammeriai.KTUgram_backend.post.PostDTO;
import com.KTUgrammeriai.KTUgram_backend.user.UserDTO;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class LikedPostDTO {
    private long id;
    private Date date;
    private Time time;
    private PostDTO post;
    private UserDTO user;
}
