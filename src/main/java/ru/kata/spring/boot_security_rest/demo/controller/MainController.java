package ru.kata.spring.boot_security_rest.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security_rest.demo.model.User;
import ru.kata.spring.boot_security_rest.demo.service.ServiceRole;
import ru.kata.spring.boot_security_rest.demo.service.ServiceUser;

import java.util.Set;

@Controller
public class MainController {
    @Autowired
    private final ServiceRole roleService;
    @Autowired
    private final ServiceUser userService;

    public MainController(ServiceRole roleService, ServiceUser userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin")
    public String getAdmin(Model model, @AuthenticationPrincipal User authUser) {
        model.addAttribute("users", userService.allUsers());
        model.addAttribute("authUser", userService.findByName(authUser.getName()).get());
        model.addAttribute("roles", roleService.findAll());
        return "admin";
    }

    @GetMapping("/user")
    public String getUser(Model userModel, @AuthenticationPrincipal User authUser) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        Set<String> roles = AuthorityUtils.authorityListToSet(auth.getAuthorities());
        boolean hasAdmin;
        hasAdmin = roles.contains("ROLE_ADMIN");
        userModel.addAttribute("authUser", authUser);
        userModel.addAttribute("hasAdmin", hasAdmin);
        userModel.addAttribute("username", name);
        return "user";
    }
}
