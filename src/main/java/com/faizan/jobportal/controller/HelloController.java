package com.faizan.jobportal.controller;

import com.faizan.jobportal.exception.JobNotFoundException;
import com.faizan.jobportal.model.Job;
import com.faizan.jobportal.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
public class HelloController {

    @Autowired
    private JobService jobservice;

    @GetMapping("/Job")
    public Page<Job> getJob(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return jobservice.getJobs(pageable);
    }

    @PostMapping("/Job")
    public ResponseEntity<String> addJob(@Valid @RequestBody Job job) {
        jobservice.addJob(job);
        return ResponseEntity.ok("Job Received");
    }

    @GetMapping("/Job/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable int id) {

        Job job = jobservice.getJobId(id);

        if (job == null) {
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
    public ResponseEntity<String> updateJob(@Valid @RequestBody Job job) {

        jobservice.updateJob(job);

        return ResponseEntity.ok("Job Updated");
    }

    @GetMapping("/Job/company/{company}")
    public List<Job> getJobByCompany(@PathVariable String company) {
        return jobservice.getCompany(company);
    }

    @GetMapping("/Job/title/{title}")
    public List<Job> getJobByTitle(@PathVariable String title) {
        return jobservice.getTitleSearch(title);
    }

    @GetMapping("/Job/title/startswith/{title}")
    public List<Job> getJobByTitleStartingWith(@PathVariable String title) {
        return jobservice.getTitleStartingWith(title);
    }

    @GetMapping("/Job/title/order")
    public List<Job> getJobByTitleOrderByTitle() {
        return jobservice.getOrderTitle();
    }

    @GetMapping("/Job/titles")
    public List<String> getAllJobTitles() {
        return jobservice.getAllJobTitles();
    }

    @GetMapping("/Job/company/{company}/location/{location}")
    public List<Job> getJobByCompanyAndLocation(
            @PathVariable String company,
            @PathVariable String location) {

        return jobservice.getJobByCompanyAndLocation(company, location);
    }

    @GetMapping("/Job/total/count")
    public Long getJobCount() {
        return jobservice.jobCount();
    }

    @GetMapping("/Job/companies")
    public List<Job> getCompanies(@RequestParam List<String> names) {
        return jobservice.getCompanies(names);
    }

    @GetMapping("/Job/id/{startId}/{endId}")
    public List<Job> getJobBetweenIds(
            @PathVariable Integer startId,
            @PathVariable Integer endId) {

        return jobservice.getJobBetweenIds(startId, endId);
    }

    @DeleteMapping("/Job/company/{company}")
    public ResponseEntity<String> deleteJobByCompany(@PathVariable String company) {

        int deleted = jobservice.deleteJobByCompany(company);

        return ResponseEntity.ok(deleted + " Job(s) deleted successfully");
    }
    @GetMapping("/search")
    public List<Job> searchJobs(
            @RequestParam(required = false) String company,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String title) {

        return jobservice.serachJobs(company, location, title);
    }
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file.");
        }
        if (file.getSize() > 5 * 1024 * 1024) {
            return ResponseEntity.badRequest().body("Maximum file size is 5 MB.");
        }
        String contentType = file.getContentType();
        if (!contentType.equals("image/png") &&
                !contentType.equals("image/jpeg") &&
                !contentType.equals("application/pdf")) {
            return ResponseEntity.badRequest().body("Only PNG, JPG and PDF files are allowed.");
        }
        Path uploadPath = Paths.get("uploads");
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        String originalFileName = file.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String uniqueFileName = UUID.randomUUID().toString() + extension;
        Path filePath = uploadPath.resolve(uniqueFileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return ResponseEntity.ok("File Uploaded Successfully");
    }
    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) throws IOException{
        Path filepath = Path.of("upload").resolve(fileName);
        byte[] file = Files.readAllBytes(filepath);
        return ResponseEntity.ok(file);
    }
}