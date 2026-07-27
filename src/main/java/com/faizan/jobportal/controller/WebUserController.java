package com.faizan.jobportal.controller;

import com.faizan.jobportal.model.WebUser;
import com.faizan.jobportal.service.WebUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class WebUserController {

    private final WebUserService service;

    public WebUserController(WebUserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public WebUser register(@RequestBody WebUser user) {

        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getRole());

        return service.register(user);
    }
}
