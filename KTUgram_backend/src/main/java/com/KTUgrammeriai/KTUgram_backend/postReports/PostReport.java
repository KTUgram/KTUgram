package com.KTUgrammeriai.KTUgram_backend.postReports;

import com.KTUgrammeriai.KTUgram_backend.post.Post;
import com.KTUgrammeriai.KTUgram_backend.user.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Table(name = "iraso_pranesimai")
@Entity
@Data
public class PostReport {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_iraso_pranesimas")
    private long id;
    @Column(name = "komentaras")
    private String reasonComment;
    @CreationTimestamp
    @Column(name = "data")
    private Date date;
    @CreationTimestamp
    @Column(name = "laikas")
    private Time time;
    @JoinColumn(name = "fk_irasas")
    @ManyToOne
    private Post post;
    @JoinColumn(name = "fk_klientas")
    @ManyToOne
    private User user;
}
