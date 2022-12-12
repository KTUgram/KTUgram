package com.KTUgrammeriai.KTUgram_backend.messages;

import com.KTUgrammeriai.KTUgram_backend.post.Post;
import com.KTUgrammeriai.KTUgram_backend.user.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Data
@Entity
@Table(name = "zinutes")
public class Message {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_zinute")
    private long id;

    @Column(name = "data")
    private Date date;

    @CreationTimestamp
    @Column(name = "laikas")
    private Time time;
    @Column(name = "turinys")
    private String content;

    @Column(name = "perskaitymo_data", nullable = true)
    private Date read_date;

    @Column(name = "perskaitymo_laikas", nullable = true)
    private Time read_time;

    @Column(name = "busena")
    private int state;

    @JoinColumn(name = "fk_klientas_gavejas")
    @ManyToOne
    private User receiver_user;
    @JoinColumn(name = "fk_klientas_rasytojas")
    @ManyToOne
    private User writer_user;

}
