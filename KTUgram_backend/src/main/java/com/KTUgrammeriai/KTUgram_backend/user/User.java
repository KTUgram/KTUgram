package com.KTUgrammeriai.KTUgram_backend.user;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="klientas")
public class User {
    @Id
    private int id;
    @Column(name = "name")
    private String username;
    private String password;
    private String email;
}
