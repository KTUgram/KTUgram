package com.KTUgrammeriai.KTUgram_backend.user;

import com.KTUgrammeriai.KTUgram_backend.person.Person;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="klientai")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_klientas")
    private long id;
    @Column(name = "valstybe")
    private String country;
    @Column(name = "miestas")
    private String city;
    @Column(name = "gimimo_data")
    private Date birthday;
    @Column(name = "apie")
    private String about;
    @Column(name = "profilio_nuotrauka")
    private String profile_pic;
    @Column(name = "statusas")
    private int status;
    @Column(name = "busena")
    private int state;
    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_asmuo", nullable = false)
    private Person person;
}
