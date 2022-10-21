package com.KTUgrammeriai.KTUgram_backend.admin;

import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin, Integer> {
    Admin findByPerson_Id(long id);

}
