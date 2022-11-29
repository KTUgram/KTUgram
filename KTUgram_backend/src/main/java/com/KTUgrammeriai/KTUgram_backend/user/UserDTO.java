package com.KTUgrammeriai.KTUgram_backend.user;
import com.KTUgrammeriai.KTUgram_backend.person.PersonDTO;
import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {
    private long id;
    private String country;
    private String city;
    private Date birthday;
    private String about;
    private String profile_pic;
    private boolean status;
    private boolean state;
    private PersonDTO person;
}
