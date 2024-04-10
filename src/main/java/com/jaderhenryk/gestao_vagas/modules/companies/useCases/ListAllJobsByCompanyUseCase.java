package com.jaderhenryk.gestao_vagas.modules.companies.useCases;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jaderhenryk.gestao_vagas.modules.companies.JobEntity;
import com.jaderhenryk.gestao_vagas.modules.companies.repositories.JobRepository;

@Service
public class ListAllJobsByCompanyUseCase {
    
    private JobRepository jobRepository;

    public ListAllJobsByCompanyUseCase(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<JobEntity> execute(UUID companyId){
        return this.jobRepository.findByCompanyId(companyId);
    }
}
