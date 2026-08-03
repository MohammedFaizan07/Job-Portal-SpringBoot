package com.faizan.jobportal.controller;

import com.faizan.jobportal.model.WebUser;
import com.faizan.jobportal.service.JwtService;
import com.faizan.jobportal.service.WebUserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class WebUserController {

    private final WebUserService service;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public WebUserController(
            WebUserService service,
            AuthenticationManager authenticationManager,
            JwtService jwtService) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }
    @PostMapping("/register")
    public WebUser register(@RequestBody WebUser user) {

        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getRole());

        return service.register(user);
    }
    @PostMapping("/login")
    public String login(@RequestBody WebUser user) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        return jwtService.generateToken(user.getUsername());
    }

    }
