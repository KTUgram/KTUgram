package com.KTUgrammeriai.KTUgram_backend.warning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarningService {
    @Autowired
    WarningRepository warningRepository;

    public void save(Warning warning){
        warningRepository.save(warning);
    }
}
