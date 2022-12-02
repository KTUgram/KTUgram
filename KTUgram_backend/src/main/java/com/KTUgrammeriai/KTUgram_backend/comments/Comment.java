package com.KTUgrammeriai.KTUgram_backend.comments;

import com.KTUgrammeriai.KTUgram_backend.post.Post;
import com.KTUgrammeriai.KTUgram_backend.user.User;
import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Data
@Entity
@Table(name = "komentarai")
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_komentaras")
    private long id;
    @Column(name = "turinys")
    private String content;
    @Column(name = "data")
    private Date date;
    @Column(name = "laikas")
    private Time time;
    @JoinColumn(name = "fk_irasas")
    @ManyToOne
    private Post post;
    @JoinColumn(name = "fk_klientas")
    @ManyToOne
    private User user;

}
