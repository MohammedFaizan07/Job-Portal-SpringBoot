package com.faizan.jobportal.repository;

import com.faizan.jobportal.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
    List<Job> findByCompanyIgnoreCase(String company);
    List<Job> findByCompanyIgnoreCaseAndLocationIgnoreCase(String company, String location);
    List<Job> findByTitleContainingIgnoreCase(String title);
    List<Job> findByTitleStartingWithIgnoreCase(String title);
    List<Job> findByOrderByTitleAsc();
}
