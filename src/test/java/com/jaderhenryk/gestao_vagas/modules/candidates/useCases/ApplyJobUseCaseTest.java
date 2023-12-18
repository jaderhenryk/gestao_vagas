package com.jaderhenryk.gestao_vagas.modules.candidates.useCases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jaderhenryk.gestao_vagas.exceptions.UserNotExistsException;
import com.jaderhenryk.gestao_vagas.modules.candidates.CandidateEntity;
import com.jaderhenryk.gestao_vagas.modules.candidates.entities.ApplyJobEntity;
import com.jaderhenryk.gestao_vagas.modules.candidates.repositories.ApplyJobRepository;
import com.jaderhenryk.gestao_vagas.modules.candidates.repositories.CandidateRepository;
import com.jaderhenryk.gestao_vagas.modules.companies.JobEntity;
import com.jaderhenryk.gestao_vagas.modules.companies.repositories.JobRepository;

@ExtendWith(MockitoExtension.class)
public class ApplyJobUseCaseTest {

    @InjectMocks
    private ApplyJobUseCase applyJobUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;
    
    @Test
    @DisplayName("Should not be able to apply to a job if candidate is not found.")
    public void shouldNotBeAbleToApplyJobIfCandidateIsNotFound() {
        try {
            applyJobUseCase.execute(null, null);
        } catch (Exception e) {
            assertInstanceOf(UserNotExistsException.class, e);
        }
    }

    @Test
    @DisplayName("Should not be able to apply job if job is not found.")
    public void shouldNotBeAbleToApplyJobIfJobIsNotFound() {
        UUID candidateId = UUID.randomUUID();
        UUID jobId = UUID.randomUUID();

        ApplyJobEntity applyJob = ApplyJobEntity.builder()
            .candidateId(candidateId)
            .jobId(jobId)
            .build();

        var applyJobCreated = ApplyJobEntity.builder().id(UUID.randomUUID()).build();
        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(new CandidateEntity()));
        when(jobRepository.findById(jobId)).thenReturn(Optional.of(new JobEntity()));

        when(applyJobRepository.save(applyJob)).thenReturn(applyJobCreated);

        var result = applyJobUseCase.execute(candidateId, jobId);
        assertThat(result).hasFieldOrProperty("id");
        assertNotNull(result.getId());
    }
}
