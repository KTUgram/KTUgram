package com.KTUgrammeriai.KTUgram_backend.user;

import com.KTUgrammeriai.KTUgram_backend.person.Person;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Table(name="klientas")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @Column(name = "apie")
    private String about;
    @Column(name = "profilio_nuotrauka")
    private String profile_pic;
    @Column(name = "statusas")
    private boolean status;
    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_asmuo", nullable = false)
    private Person person;
}
