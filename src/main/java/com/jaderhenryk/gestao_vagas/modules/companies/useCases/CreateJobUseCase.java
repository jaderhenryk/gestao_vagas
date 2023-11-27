package com.jaderhenryk.gestao_vagas.modules.companies.useCases;

import org.springframework.stereotype.Service;

import com.jaderhenryk.gestao_vagas.modules.companies.JobEntity;
import com.jaderhenryk.gestao_vagas.modules.companies.repositories.JobRepository;

@Service
public class CreateJobUseCase {
    
    private JobRepository jobRepository;

    public CreateJobUseCase(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    
    public JobEntity execute(JobEntity jobEntity) {
        return this.jobRepository.save(jobEntity);
    }
}
