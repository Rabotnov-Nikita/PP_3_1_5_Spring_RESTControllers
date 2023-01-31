package ru.kata.spring.boot_security_rest.demo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security_rest.demo.model.Role;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findById(Long id);

    Optional<Role> findByRole(String role);

    List<Role> findAll();

    Role save(Role role);

    void deleteById(Long id);
}