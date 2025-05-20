package com.coder.auth.repository;

import com.coder.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Boolean existsByUsername(String userName);


    Boolean existsByEmail(String email);


    Optional<User> findByUsername(String username);
}
