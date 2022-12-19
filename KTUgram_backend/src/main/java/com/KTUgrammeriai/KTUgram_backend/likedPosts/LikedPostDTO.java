package com.KTUgrammeriai.KTUgram_backend.likedPosts;
import com.KTUgrammeriai.KTUgram_backend.post.PostDTO;
import com.KTUgrammeriai.KTUgram_backend.user.UserDTO;
import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class LikedPostDTO {
    private long id;
    private Date date;
    private LocalTime time;
    private PostDTO post;
    private UserDTO user;
}
