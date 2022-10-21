package com.KTUgrammeriai.KTUgram_backend.admin;

import com.KTUgrammeriai.KTUgram_backend.person.Person;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "administratorius")
public class Admin {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @Column(name = "pareigos")
    private String position;
    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_asmuo", nullable = false)
    private Person person;
}
