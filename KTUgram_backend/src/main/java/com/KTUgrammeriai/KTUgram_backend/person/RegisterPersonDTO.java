package com.KTUgrammeriai.KTUgram_backend.person;

import lombok.Data;

@Data
public class RegisterPersonDTO {
    private int id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String surname;
}
