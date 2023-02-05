package ru.kata.spring.boot_security_rest.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security_rest.demo.model.User;

import ru.kata.spring.boot_security_rest.demo.service.ServiceUser;

@RestController
@RequestMapping("/user")
public class UserRestController {

    private final ServiceUser userService;

    public UserRestController(ServiceUser userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/info")
    public ResponseEntity<User> userPage(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(userService.findByName(user.getName()).get());
    }
}
