package com.jaderhenryk.gestao_vagas.modules.candidates.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jaderhenryk.gestao_vagas.modules.candidates.CandidateEntity;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
    
    Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);
    Optional<CandidateEntity> findByUsername(String username);
}
