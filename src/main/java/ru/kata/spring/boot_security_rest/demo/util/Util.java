package ru.kata.spring.boot_security_rest.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security_rest.demo.model.Role;
import ru.kata.spring.boot_security_rest.demo.model.User;
import ru.kata.spring.boot_security_rest.demo.service.ServiceRole;
import ru.kata.spring.boot_security_rest.demo.service.ServiceUser;

import java.util.HashSet;
import java.util.Set;

@Component
public class Util {

    @Autowired
    @Lazy
    ServiceUser serviceUser;

    @Autowired
    ServiceRole serviceRole;

    Set<Role> roleSet = new HashSet<>();

    @Transactional
    public void generateUsers() {
        roleSet.add(serviceRole.saveRole(new Role("ROLE_USER")));
        roleSet.add(serviceRole.saveRole(new Role("ROLE_ADMIN")));
        //password: password
        serviceUser.createUser(new User(1L, "admin", "adminov", "admin", 1, "admin@mail.ru" ,roleSet));
        serviceUser.createUser(new User(2L, "user", "userov", "user", 1, "user@mail.ru" ,roleSet));
    }
}