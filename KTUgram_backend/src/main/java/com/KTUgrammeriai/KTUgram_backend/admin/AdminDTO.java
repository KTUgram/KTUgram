package com.KTUgrammeriai.KTUgram_backend.admin;

import com.KTUgrammeriai.KTUgram_backend.person.Person;
import lombok.Data;

@Data
public class AdminDTO {
    private int id;
    private String position;
    private Person person;
}
