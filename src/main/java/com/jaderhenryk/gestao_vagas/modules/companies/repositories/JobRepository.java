package com.jaderhenryk.gestao_vagas.modules.companies.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jaderhenryk.gestao_vagas.modules.companies.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
    
}
