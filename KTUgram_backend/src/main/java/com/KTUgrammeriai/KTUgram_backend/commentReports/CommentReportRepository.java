package com.KTUgrammeriai.KTUgram_backend.commentReports;

import com.KTUgrammeriai.KTUgram_backend.comments.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentReportRepository extends CrudRepository<CommentReport, Long>{
    List<CommentReport> findByComment_IdEquals(long id);
    void deleteByComment_IdEquals(long id);
    int countByComment_IdEquals(long id);

}
