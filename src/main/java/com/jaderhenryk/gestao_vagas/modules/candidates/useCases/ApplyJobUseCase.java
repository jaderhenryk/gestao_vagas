package com.jaderhenryk.gestao_vagas.modules.candidates.useCases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jaderhenryk.gestao_vagas.exceptions.JobNotFoundException;
import com.jaderhenryk.gestao_vagas.exceptions.UserNotExistsException;
import com.jaderhenryk.gestao_vagas.modules.candidates.entities.ApplyJobEntity;
import com.jaderhenryk.gestao_vagas.modules.candidates.repositories.ApplyJobRepository;
import com.jaderhenryk.gestao_vagas.modules.candidates.repositories.CandidateRepository;
import com.jaderhenryk.gestao_vagas.modules.companies.repositories.JobRepository;

@Service
public class ApplyJobUseCase {

    private CandidateRepository candidateRepository;

    private JobRepository jobRepository;

    private ApplyJobRepository applyJobRepository;

    public ApplyJobUseCase(
        CandidateRepository candidateRepository,
        JobRepository jobRepository,
        ApplyJobRepository applyJobRepository
    ) {
        this.candidateRepository = candidateRepository;
        this.jobRepository = jobRepository;
        this.applyJobRepository = applyJobRepository;
    }
    
    public ApplyJobEntity execute(UUID candidateId, UUID jobId) {
        this.candidateRepository.findById(candidateId)
            .orElseThrow(() -> {
                throw new UserNotExistsException();
            });
        this.jobRepository.findById(jobId)
            .orElseThrow(() -> {
                throw new JobNotFoundException();
            });

        ApplyJobEntity applyJob = ApplyJobEntity.builder()
            .candidateId(candidateId)
            .jobId(jobId)
            .build();

        applyJob = applyJobRepository.save(applyJob);
        return applyJob;
    }
}
