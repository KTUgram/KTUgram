package com.KTUgrammeriai.KTUgram_backend.postReports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostReportService {

    @Autowired
    PostReportRepository postReportRepository;

    public void save(PostReport postReport){
        postReportRepository.save(postReport);
    }

}
