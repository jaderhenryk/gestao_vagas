package com.jaderhenryk.gestao_vagas.modules.companies.useCases;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jaderhenryk.gestao_vagas.modules.companies.JobEntity;
import com.jaderhenryk.gestao_vagas.modules.companies.repositories.JobRepository;

@Service
public class ListAllJobsByFilterUseCase {

    private JobRepository jobRepository;

    public ListAllJobsByFilterUseCase(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    
    public List<JobEntity> execute(String filter) {
        return this.jobRepository.findByDescriptionContainingIgnoreCase(filter);
    }
}
