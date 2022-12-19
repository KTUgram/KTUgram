package com.KTUgrammeriai.KTUgram_backend.likedPosts;

import com.KTUgrammeriai.KTUgram_backend.post.Post;
import com.KTUgrammeriai.KTUgram_backend.user.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Data
@Entity
@Table(name = "pamegti_irasai")
public class LikedPost {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_pamegtas_irasas")
    private long id;
    @Column(name = "data", nullable = true)
    private Date date = new Date();
    @Column(name = "laikas", nullable = true)
    private LocalTime time = LocalTime.now();
    @JoinColumn(name = "fk_irasas")
    @ManyToOne
    private Post post;
    @JoinColumn(name = "fk_klientas")
    @ManyToOne
    private User user;
}
