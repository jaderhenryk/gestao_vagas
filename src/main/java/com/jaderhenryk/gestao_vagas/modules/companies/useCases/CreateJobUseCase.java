package com.jaderhenryk.gestao_vagas.modules.companies.useCases;

import org.springframework.stereotype.Service;

import com.jaderhenryk.gestao_vagas.exceptions.CompanyNotFoundException;
import com.jaderhenryk.gestao_vagas.modules.companies.JobEntity;
import com.jaderhenryk.gestao_vagas.modules.companies.repositories.CompanyRepository;
import com.jaderhenryk.gestao_vagas.modules.companies.repositories.JobRepository;

@Service
public class CreateJobUseCase {
    
    private JobRepository jobRepository;

    private CompanyRepository companyRepository;

    public CreateJobUseCase(
        JobRepository jobRepository,
        CompanyRepository companyRepository
    ) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
    }
    
    public JobEntity execute(JobEntity jobEntity) {
        companyRepository.findById(jobEntity.getCompanyId()).orElseThrow(() -> {
            throw new CompanyNotFoundException();
        });
        return this.jobRepository.save(jobEntity);
    }
}
