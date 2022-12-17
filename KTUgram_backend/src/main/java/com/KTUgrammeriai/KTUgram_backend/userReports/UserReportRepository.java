package com.KTUgrammeriai.KTUgram_backend.userReports;

import com.KTUgrammeriai.KTUgram_backend.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserReportRepository extends CrudRepository<UserReport, Long> {

    public List<UserReport> findByReportedUser_IdEquals(long id);
    public long countByReportedUser_IdEquals(long id);
}
