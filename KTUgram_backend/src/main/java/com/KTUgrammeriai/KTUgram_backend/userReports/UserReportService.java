package com.KTUgrammeriai.KTUgram_backend.userReports;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserReportService {
    @Autowired
    public UserReportRepository userReportRepository;
}
