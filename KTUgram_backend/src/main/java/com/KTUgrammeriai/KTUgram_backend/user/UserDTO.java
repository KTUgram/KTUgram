package com.KTUgrammeriai.KTUgram_backend.user;

import com.KTUgrammeriai.KTUgram_backend.person.PersonDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class UserDTO {
    private long id;
    private String about;
    private String profile_pic;
    private boolean status;
    private PersonDTO person;
}
