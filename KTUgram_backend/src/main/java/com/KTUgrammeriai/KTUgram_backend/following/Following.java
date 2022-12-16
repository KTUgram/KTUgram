package com.KTUgrammeriai.KTUgram_backend.following;

import com.KTUgrammeriai.KTUgram_backend.user.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Data
@Entity
@Table(name = "sekami_klientai")
public class Following {
    @Id
    @Column(name = "id_sekamas_klientas")
    private long id;
    @JoinColumn(name = "fk_klientas_sekamas")
    @ManyToOne
    private User following;
    @JoinColumn(name = "fk_klientas_sekejas")
    @ManyToOne
    private User follower;
    @Column(name = "data")
    private java.util.Date date = new Date();
    @Column(name = "laikas")
    private LocalTime time = LocalTime.now();
}
