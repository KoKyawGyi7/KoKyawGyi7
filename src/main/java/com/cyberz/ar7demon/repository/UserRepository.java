package com.cyberz.ar7demon.repository;

import com.cyberz.ar7demon.model.entity.User;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepositoryImplementation<User,Integer> {
    Optional<User> findByEmail(String username);

    boolean existsByEmail(String email);
}
