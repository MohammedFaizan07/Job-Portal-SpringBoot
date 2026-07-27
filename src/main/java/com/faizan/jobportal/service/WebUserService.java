package com.faizan.jobportal.service;

import com.faizan.jobportal.model.WebUser;
import com.faizan.jobportal.repository.WebUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class WebUserService {

    private final WebUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public WebUserService(WebUserRepository repository,
                          PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public WebUser register(WebUser user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return repository.save(user);
    }
}