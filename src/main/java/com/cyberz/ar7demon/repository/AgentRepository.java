package com.cyberz.ar7demon.repository;

import com.cyberz.ar7demon.model.entity.Agent;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgentRepository extends JpaRepositoryImplementation<Agent,Integer> {
    Optional<Agent> findByEmail(String email);
}
