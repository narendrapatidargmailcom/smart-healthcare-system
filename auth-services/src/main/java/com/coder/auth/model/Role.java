package com.coder.auth.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Table(name = "role")
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserRole name;


    public Long getId() {
        return id;
    }

    public UserRole getUserRole() {
        return name;
    }

//    public Set<User> getUsers() {
//        return users;
//    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserRole(UserRole userRole) {
        this.name = userRole;
    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }

    public Role(Long id, UserRole userRole, Set<User> users) {
        this.id = id;
        this.name = userRole;
    }

    public Role(){

    }
}
