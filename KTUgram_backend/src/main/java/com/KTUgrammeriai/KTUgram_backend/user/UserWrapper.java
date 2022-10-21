package com.KTUgrammeriai.KTUgram_backend.user;

import com.KTUgrammeriai.KTUgram_backend.person.PersonDTO;
import lombok.Data;

@Data
public class UserWrapper {
    PersonDTO personDTO;
    UserDTO userDTO;
}
