package ru.kata.spring.boot_security_rest.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security_rest.demo.dao.UserRepository;
import ru.kata.spring.boot_security_rest.demo.model.User;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceUserImpl implements ServiceUser {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public ServiceUserImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(User user) {
        User updatedUser = userRepository.findById(user.getId()).get();
        if (!user.getFirstname().equals("")) updatedUser.setFirstname(user.getFirstname());
        if (!user.getSurname().equals("")) updatedUser.setSurname(user.getSurname());
        if (user.getAge() != null) updatedUser.setAge(user.getAge());
        if (!user.getName().equals("")) updatedUser.setName(user.getName());
        if (!user.getPassword().equals("")) updatedUser.setPassword(passwordEncoder.encode(user.getPassword()));
        if (!user.getRoleSet().isEmpty()) updatedUser.setRoleSet(user.getRoleSet());
        return userRepository.save(updatedUser);
    }
}