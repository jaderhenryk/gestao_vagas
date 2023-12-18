package com.jaderhenryk.gestao_vagas.modules.candidates.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jaderhenryk.gestao_vagas.modules.candidates.entities.ApplyJobEntity;

public interface ApplyJobRepository extends JpaRepository<ApplyJobEntity, UUID> {
    
}
