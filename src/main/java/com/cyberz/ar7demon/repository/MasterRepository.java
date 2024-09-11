package com.cyberz.ar7demon.repository;

import com.cyberz.ar7demon.model.entity.Master;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MasterRepository extends JpaRepositoryImplementation<Master,Integer> {
    Optional<Master> findByEmail(String email);
}
