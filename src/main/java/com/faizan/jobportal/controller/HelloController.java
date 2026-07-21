package com.faizan.jobportal.controller;

import com.faizan.jobportal.exception.JobNotFoundException;
import com.faizan.jobportal.model.Job;
import com.faizan.jobportal.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
public class HelloController {
    @Autowired
    private JobService jobservice;

    @GetMapping("/Job")
    public List<Job> getJob() {
        return jobservice.getJobs();
    }

    @PostMapping("/Job")
    public ResponseEntity<String> addJob(@Valid @RequestBody Job job) {
        jobservice.addJob(job);
        return ResponseEntity.ok( "Job Received");
    }

    @GetMapping("/Job/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable int id) {
        Job job = jobservice.getJobId(id);
        if(job == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(job);
    }

    @DeleteMapping("/Job/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable int id) {
            Job job = jobservice.getJobId(id);
            if (job == null) {
                throw new JobNotFoundException();
            }
            jobservice.deleteJob(id);
            return ResponseEntity.ok("Job Deleted Successfully");
    }

   @PutMapping("/Job")
    public ResponseEntity<String> updateJob(@Valid @RequestBody Job job){
        jobservice.updateJob(job);
        return ResponseEntity.ok("Job Updated");
   }

   @GetMapping("/Job/company/{company}")
    public List<Job> getJobByCompany(@PathVariable String company){
        return jobservice.getCompany(company);
   }
   @GetMapping("/Job/title/{title}")
    public List<Job> getJobByTitle(@PathVariable String title){
        return jobservice.getTitleSearch(title);
   }
    @GetMapping("/Job/title/startswith/{title}")
    public List<Job> getJobByTitleStartingWith(@PathVariable String title){
        return jobservice.getTitleStartingWith(title);
    }
    @GetMapping("/Job/title/order")
    public List<Job> getJobByTitleOrderByTitle(){
        return jobservice.getOrderTitle();
    }
    @GetMapping("/Job/titles")
    public List<String> getAllJobTitles(){
        return jobservice.getAllJobTitles();
    }
    @GetMapping("/Job/company/{company}/location/{location}")
    public List<Job> getJobByCompanyAndLocation(@PathVariable String company,@PathVariable String location){
        return jobservice.getJobByCompanyAndLocation(company,location);
    }
    @GetMapping("/Job/total/count")
    public Long jobCount(){
        return jobservice.jobCount();
    }
    @GetMapping("/Job/companies")
    public List<Job> getCompanies(@RequestParam List<String> names){
        return jobservice.getCompanies(names);
    }
    @GetMapping("/Job/id/{startid}/{endid}")
    public List<Job> getJobBetweenIds(@PathVariable Integer startId , @PathVariable Integer endId){
        return jobservice.getJobBetweenIds(startId,endId);
    }
    @GetMapping("/Job/company/{company}")
    public ResponseEntity<String> deleteJobByCompany(@PathVariable String company){
        int deleted = jobservice.deleteJobByCompany(company);
        return ResponseEntity.ok(deleted + "Job deleted successfully");
    }
 }