package com.KTUgrammeriai.KTUgram_backend.user;

import com.KTUgrammeriai.KTUgram_backend.person.Person;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class UserDTO {
    private int id;
    private String about;
    private byte[] profile_pic;
    private boolean status;
    private Person person;
}
