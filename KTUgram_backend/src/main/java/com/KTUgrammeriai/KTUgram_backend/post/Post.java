package com.KTUgrammeriai.KTUgram_backend.post;

import com.KTUgrammeriai.KTUgram_backend.user.User;
import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Data
@Table(name = "irasai")
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_irasas")
    private long id;
    @Column(name = "data", nullable = true)
    private Date date;
    @Column(name = "laikas", nullable = true)
    private Time time;
    @Column(name = "apie")
    private String about;
    @Column(name = "turinys")
    private String content;
    @Column(name = "vietove", nullable = true)
    private String location;
    @Column(name = "pavadinimas")
    private String title;
    @Column(name = "busena")
    private String state;
    @ManyToOne
    @JoinColumn(name = "fk_klientas", nullable = false)
    private User user;
}
