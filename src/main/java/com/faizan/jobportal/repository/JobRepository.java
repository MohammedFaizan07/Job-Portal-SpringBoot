package com.faizan.jobportal.repository;

import com.faizan.jobportal.model.Job;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer>, JpaSpecificationExecutor<Job> {
    List<Job> findByCompanyIgnoreCase(String company);
    List<Job> findByTitleContainingIgnoreCase(String title);
    List<Job> findByTitleStartingWithIgnoreCase(String title);
    List<Job> findByOrderByTitleAsc();
    @Query("Select j.title From Job j")
    List<String> getAllJobTitles();
    @Query("Select j From Job j Where j.company = :company and j.location = :location")
    List<Job> getJobByCompanyAndLocation(@Param("company") String company , @Param("location") String location);
    @Query("Select count(j) From Job j ")
    Long jobCount();
    @Query("Select j From Job j Where j.company IN :companies")
    List<Job> getCompanies(@Param("companies") List<String> companies);
    @Query("SELECT j FROM Job j WHERE j.id BETWEEN :startId AND :endId")
    List<Job> getJobBetweenIds(@Param("startId") Integer startId,
                               @Param("endId") Integer endId);
    @Transactional
    @Modifying
    @Query("Delete From Job j Where j.company = :company ")
    int deleteJobByCompany(@Param("company") String company);
}
