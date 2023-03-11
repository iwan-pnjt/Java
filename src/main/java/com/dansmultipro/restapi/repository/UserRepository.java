package com.dansmultipro.restapi.repository;

import com.dansmultipro.restapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameandPassword(String username, String password);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
}
