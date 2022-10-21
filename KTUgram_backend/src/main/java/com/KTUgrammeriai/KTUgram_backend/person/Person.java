package com.KTUgrammeriai.KTUgram_backend.person;

import com.KTUgrammeriai.KTUgram_backend.user.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "asmuo")
public class Person {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @Column(name = "slapyvardis")
    private String username;
    @Column(name = "slaptazodis")
    private String password;
    @Column(name = "el_pastas")
    private String email;
    @Column(name = "vardas")
    private String name;
    @Column(name = "pavarde")
    private String surname;
}
