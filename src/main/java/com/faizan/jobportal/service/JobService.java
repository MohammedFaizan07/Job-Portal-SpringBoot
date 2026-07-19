package com.faizan.jobportal.service;
import com.faizan.jobportal.model.Job;
import org.springframework.stereotype.Service;
import com.faizan.jobportal.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
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
    public Job getJobId(int id){
        return jobrepository.findById(id).orElse(null);
    }
    public void deleteJob(int id){
        jobrepository.deleteById(id);
    }
    public void updateJob(Job job){
        jobrepository.save(job);
    }



















}
