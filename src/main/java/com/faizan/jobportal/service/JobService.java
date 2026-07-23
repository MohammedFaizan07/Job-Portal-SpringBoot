package com.faizan.jobportal.service;
import com.faizan.jobportal.model.Job;
import com.faizan.jobportal.specification.JobSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.faizan.jobportal.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service

public class JobService {
    @Autowired
    private JobRepository jobrepository;

    public Page<Job> getJobs(Pageable pageable) {
        return jobrepository.findAll(pageable);
    }

    public void addJob(Job job) {
        jobrepository.save(job);
    }

    public Job getJobId(int id) {
        return jobrepository.findById(id).orElse(null);
    }

    public void deleteJob(int id) {
        jobrepository.deleteById(id);
    }

    public void updateJob(Job job) {
        jobrepository.save(job);
    }
    public List<Job> getCompany(String company) {
        return jobrepository.findByCompanyIgnoreCase(company);
    }
    public List<Job> getTitleSearch(String title) {
        return jobrepository.findByTitleContainingIgnoreCase(title);
    }
    public List<Job> getTitleStartingWith(String title) {
        return jobrepository.findByTitleStartingWithIgnoreCase(title);
    }
    public List<Job> getOrderTitle() {
        return jobrepository.findByOrderByTitleAsc();
    }
    public List<String> getAllJobTitles(){
        return jobrepository.getAllJobTitles();
    }
    public List<Job> getJobByCompanyAndLocation(String company , String location){
        return jobrepository.getJobByCompanyAndLocation(company,location);
    }
    public Long jobCount(){
        return jobrepository.jobCount();
    }
    public List<Job> getCompanies(List<String> companies){
        return jobrepository.getCompanies(companies);
    }
    public List<Job> getJobBetweenIds(Integer startId, Integer endId){
        return jobrepository.getJobBetweenIds(startId,endId);
    }
    public int deleteJobByCompany(String company){
        return jobrepository.deleteJobByCompany(company);
    }
    public List<Job> serachJobs(String company, String location , String title){
        Specification<Job> specification = Specification.unrestricted();
        if (company != null) {
            specification = specification.and(JobSpecification.hasCompany(company));
        }

        if (location != null) {
            specification = specification.and(JobSpecification.hasLocation(location));
        }

        if (title != null) {
            specification = specification.and(JobSpecification.hasTitle(title));
        }

        return jobrepository.findAll(specification);
    }
 }




















