package com.jaderhenryk.gestao_vagas.modules.candidates.useCases;

import java.util.UUID;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jaderhenryk.gestao_vagas.modules.candidates.CandidateEntity;
import com.jaderhenryk.gestao_vagas.modules.candidates.dto.ProfileCandidateResponseDTO;
import com.jaderhenryk.gestao_vagas.modules.candidates.repositories.CandidateRepository;

@Service
public class ProfileCandidateUseCase {
    
    private CandidateRepository candidateRepository;

    public ProfileCandidateUseCase(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public ProfileCandidateResponseDTO execute(UUID candidateId) {
        CandidateEntity candidate = this.candidateRepository.findById(candidateId)
            .orElseThrow(() -> {
                throw new UsernameNotFoundException("User not found.");
            });
        ProfileCandidateResponseDTO candidateDto = ProfileCandidateResponseDTO.builder()
            .description(candidate.getDescription())
            .name(candidate.getName())
            .email(candidate.getEmail())
            .username(candidate.getUsername())
            .id(candidate.getId())
            .build();
        return candidateDto;
    }
}
