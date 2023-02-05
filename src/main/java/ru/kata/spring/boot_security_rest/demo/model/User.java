package ru.kata.spring.boot_security_rest.demo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;

    private String surname;

    private String password;

    private Integer age;

    @Column(name = "Email")
    private String name;

    @ManyToMany
    private Set<Role> roleSet;

    public User() {
    }

    public User(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(String name, String password, Set<Role> roleSet) {
        this.name = name;
        this.password = password;
        this.roleSet = roleSet;
    }

    public User(Long id, String name, Set<Role> roleSet) {
        this.id = id;
        this.name = name;
        this.roleSet = roleSet;
    }

    public User(Long id, String name, String password, Set<Role> roleSet) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.roleSet = roleSet;
    }

    public User(Long id, String name, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.roleSet = (Set<Role>) authorities;
    }

    public User(Long id, String firstname, String surname, String password, Integer age, String name, Set<Role> roleSet) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.password = password;
        this.age = age;
        this.name = name;
        this.roleSet = roleSet;
    }

    public User(Long id, String firstname, String surname, String password, Integer age, String name, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.password = password;
        this.age = age;
        this.name = name;
        this.roleSet = (Set<Role>) authorities;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return firstname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleSet;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getRoleSetString() {

        StringBuilder s = new StringBuilder();

        for (Role r : roleSet) {
            if (s.length() != 0) {
                s.append(", ");
            }
            s.append(r.getRole());
        }
        return String.valueOf(s);
    }
}