package com.KTUgrammeriai.KTUgram_backend.post;

import com.KTUgrammeriai.KTUgram_backend.user.User;
import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Data
@Table(name = "irasas")
@Entity
public class Post {
    @Id
    private long id;
    @Column(name = "data")
    private Date date;
    @Column(name = "laikas")
    private Time time;
    @Column(name = "apie")
    private String about;
    @Column(name = "turinys")
    private String content;
    @Column(name = "vietove")
    private String location;
    @Column(name = "pavadinimas")
    private String title;
    @Column(name = "busena")
    private String state;
    @ManyToOne
    @JoinColumn(name = "fk_klientas", nullable = false)
    private User user;
}
