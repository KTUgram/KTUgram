package com.KTUgrammeriai.KTUgram_backend.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByPerson_Id(long id);
}
