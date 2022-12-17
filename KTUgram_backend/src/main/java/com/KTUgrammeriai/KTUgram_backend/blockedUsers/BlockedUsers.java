package com.KTUgrammeriai.KTUgram_backend.blockedUsers;

import com.KTUgrammeriai.KTUgram_backend.user.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Data
@Entity
@Table(name = "blokuoti_klientai")
public class BlockedUsers {
    @Id
    @Column(name = "id_blokuotas_klientas")
    private long id;
    @JoinColumn(name = "fk_klientas_blokuotojas")
    @ManyToOne
    private User blockerUser;
    @JoinColumn(name = "fk_klientas_blokuojamas")
    @ManyToOne
    private User blockedUser;
    @Column(name = "data")
    private Date date = new Date();
    @Column(name = "laikas")
    private LocalTime time = LocalTime.now();
}
