package com.KTUgrammeriai.KTUgram_backend.person;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {
    Person findPersonByUsername(String username);
    Person findPersonById(long id);
}
