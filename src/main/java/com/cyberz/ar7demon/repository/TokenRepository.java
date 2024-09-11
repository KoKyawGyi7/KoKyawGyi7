package com.cyberz.ar7demon.repository;

import com.cyberz.ar7demon.model.entity.AuthenticationToken;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepositoryImplementation<AuthenticationToken,Integer> {
}
