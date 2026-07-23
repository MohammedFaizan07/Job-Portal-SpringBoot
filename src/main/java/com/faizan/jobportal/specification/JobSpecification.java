package com.faizan.jobportal.specification;

import com.faizan.jobportal.model.Job;
import org.springframework.data.jpa.domain.Specification;

public class JobSpecification {

    public static Specification<Job> hasCompany(String company) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("company"), company);
    }

    public static Specification<Job> hasLocation(String location) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("location"), location);
    }

    public static Specification<Job> hasTitle(String title) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("title"), title);
    }
}