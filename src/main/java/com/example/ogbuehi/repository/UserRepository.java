package com.example.ogbuehi.repository;

import com.example.ogbuehi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsernameIgnoreCase(String username);
//    boolean existsByEmailIgnoreCase(String email);

    Optional<User> findByUsername(String username);
}
