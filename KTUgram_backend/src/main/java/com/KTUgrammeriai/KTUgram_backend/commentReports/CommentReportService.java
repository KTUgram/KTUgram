package com.KTUgrammeriai.KTUgram_backend.commentReports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentReportService {

    @Autowired
    public CommentReportRepository commentReportRepository;

    public void save(CommentReport commentReport){
        commentReportRepository.save(commentReport);
    }
}
