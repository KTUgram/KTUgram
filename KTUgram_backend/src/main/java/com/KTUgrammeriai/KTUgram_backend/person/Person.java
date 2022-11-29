package com.KTUgrammeriai.KTUgram_backend.person;

import com.KTUgrammeriai.KTUgram_backend.user.User;
import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Data
@Entity
@Table(name = "asmenys")
public class Person {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_asmuo")
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
    @Column(name = "prisijungimo_data")
    private Date register_date;
    @Column(name = "telefono_nr")
    private String phone_nr;
    @Column(name = "paskutines_veiklos_data")
    private Date last_active_date;
    @Column(name = "paskutines_veiklos_laikas")
    private Time last_active_time;
}
