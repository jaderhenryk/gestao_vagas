package com.jaderhenryk.gestao_vagas.modules.candidates.useCases;

import org.springframework.stereotype.Service;

import com.jaderhenryk.gestao_vagas.exceptions.UserAlreadyExistsException;
import com.jaderhenryk.gestao_vagas.modules.candidates.CandidateEntity;
import com.jaderhenryk.gestao_vagas.modules.candidates.CandidateRepository;

@Service
public class CreateCandidateUseCase {

    private CandidateRepository candidateRepository;

    public CreateCandidateUseCase(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }
    
    public CandidateEntity execute(CandidateEntity candidate) {
        this.candidateRepository.findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail()).ifPresent(user -> {
            throw new UserAlreadyExistsException();
        });
        return this.candidateRepository.save(candidate);
    }
}
