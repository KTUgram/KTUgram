package com.KTUgrammeriai.KTUgram_backend.person;

import lombok.Data;

@Data
public class PersonDTO {
    private long id;
    private String username;
    private String email;
    private String name;
    private String surname;
}
