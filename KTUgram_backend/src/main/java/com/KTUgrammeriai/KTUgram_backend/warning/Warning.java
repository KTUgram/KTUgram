package com.KTUgrammeriai.KTUgram_backend.warning;

import com.KTUgrammeriai.KTUgram_backend.admin.Admin;
import com.KTUgrammeriai.KTUgram_backend.user.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "pranesimai_klientam")
@Data
public class Warning {
    @Id
    @Column(name = "id_pranesimas_klientui")
    private long id;
    @Column(name = "data")
    private Date date = new Date();
    @Column(name = "laikas")
    private LocalTime time = LocalTime.now();
    @Column(name = "komentaras")
    private String comment;
    @JoinColumn(name = "fk_administratorius")
    @ManyToOne
    private Admin admin;
    @JoinColumn(name = "fk_klientas")
    @ManyToOne
    private User user;
}
