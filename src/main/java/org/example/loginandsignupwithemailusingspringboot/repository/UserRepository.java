package org.example.loginandsignupwithemailusingspringboot.repository;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String email);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
