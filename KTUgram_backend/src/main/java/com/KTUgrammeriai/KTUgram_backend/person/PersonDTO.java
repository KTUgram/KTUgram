package com.KTUgrammeriai.KTUgram_backend.person;

import lombok.Data;
import java.sql.Time;
import java.util.Date;

@Data
public class PersonDTO {
    private long id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String surname;
    private Date register_date;
    private String phone_nr;
    private Date last_active_date;
    private Time last_active_time;
}
