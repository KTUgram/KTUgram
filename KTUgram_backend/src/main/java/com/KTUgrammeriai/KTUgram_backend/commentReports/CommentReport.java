package com.KTUgrammeriai.KTUgram_backend.commentReports;

import com.KTUgrammeriai.KTUgram_backend.comments.Comment;
import com.KTUgrammeriai.KTUgram_backend.user.User;
import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Data
@Entity
@Table(name = "komentaro_pranesimai")
public class CommentReport {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_komentaro_pranesimas")
    private long id;
    @Column(name = "priezastis")
    private int reason;
    @Column(name = "komentaras")
    private String reasonComment;
    @Column(name = "data")
    private Date date;
    @Column(name = "laikas")
    private Time time;
    @JoinColumn(name = "fk_komentaras")
    @ManyToOne
    private Comment comment;
    @JoinColumn(name = "fk_klientas")
    @ManyToOne
    private User user;

}
