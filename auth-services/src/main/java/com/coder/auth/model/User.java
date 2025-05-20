package com.coder.auth.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    @Size(max = 50)
    private String email;

    @NotBlank
    @Size(max = 120)
    private String Password;

    @ManyToMany()
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id" ,referencedColumnName = "id"))
    private Set<Role> roles=new HashSet<>();


    public User( String email, String username, String password, Set<Role> roles) {
        this.email = email;
        this.username = username;
        Password = password;
        this.roles = roles;
    }
    public User( String email, String username, String password) {
        this.email = email;
        this.username = username;
        Password = password;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return Password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public User() {
    }
}
