package com.faizan.jobportal.service;
import com.faizan.jobportal.model.Job;
import org.springframework.stereotype.Service;
import com.faizan.jobportal.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service

public class JobService {
    @Autowired
    private JobRepository jobrepository;

    public List<Job> getJobs() {
        return jobrepository.findAll();
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
 }




















