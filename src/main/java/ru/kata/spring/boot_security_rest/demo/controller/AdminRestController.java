package ru.kata.spring.boot_security_rest.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security_rest.demo.model.User;
import ru.kata.spring.boot_security_rest.demo.service.ServiceUser;


import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminRestController {


    private final ServiceUser userService;


    public AdminRestController(ServiceUser userService) {

        this.userService = userService;

    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> getUsers(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.findById(id).get());
    }


    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.allUsers());
    }

    @PostMapping(value = "/add")
    public ResponseEntity<User> addUser(@ModelAttribute User user) {
        return ResponseEntity.ok().body(userService.createUser(user));
    }

    @PatchMapping(value = "/update")
    public ResponseEntity<User> updateUser(@ModelAttribute User user) {
        return ResponseEntity.ok().body(userService.updateUser(user));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().body(true);
    }
}
