package com.KTUgrammeriai.KTUgram_backend.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;

    public Admin findByPersonId(long personId){
        return adminRepository.findByPerson_Id(personId);
    }
}
