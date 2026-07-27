package com.faizan.jobportal.repository;

import com.faizan.jobportal.model.WebUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface WebUserRepository extends JpaRepository<WebUser,String> {
    Optional<WebUser> findByUsername(String username);
}
