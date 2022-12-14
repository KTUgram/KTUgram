package com.KTUgrammeriai.KTUgram_backend.user;

import com.KTUgrammeriai.KTUgram_backend.person.PersonDTO;
import com.KTUgrammeriai.KTUgram_backend.person.RegisterPersonDTO;
import lombok.Data;
import java.util.Date;

@Data
public class RegisterUserDTO {
    private long id;
    private String country;
    private String city;
    private Date birthday;
    private String about;
    private String profile_pic;
    private int status;
    private int state;
    private RegisterPersonDTO person;
}
