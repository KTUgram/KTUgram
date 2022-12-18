package com.KTUgrammeriai.KTUgram_backend.userReports;


import com.KTUgrammeriai.KTUgram_backend.user.User;
import com.KTUgrammeriai.KTUgram_backend.user.UserDTO;
import lombok.Data;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalTime;
import java.util.Date;

@Data
@Entity
@Table(name="klientu_pranesimai")
public class UserReport {
    @Id
    @Column(name = "id_kliento_pranesimas")
    private long id;
    @Column(name = "komentaras")
    private String reasonComment;
    @CreationTimestamp
    @Column(name = "data")
    private Date date = new Date();
    @CreationTimestamp
    @Column(name = "laikas")
    private LocalTime time = LocalTime.now();
    @JoinColumn(name = "fk_klientas_pranesejas")
    @ManyToOne
    private User reporter;
    @JoinColumn(name = "fk_klientas_gavejas")
    @ManyToOne
    private User reportedUser;
}