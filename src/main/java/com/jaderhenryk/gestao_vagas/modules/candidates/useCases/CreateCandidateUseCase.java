package com.jaderhenryk.gestao_vagas.modules.candidates.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jaderhenryk.gestao_vagas.exceptions.UserAlreadyExistsException;
import com.jaderhenryk.gestao_vagas.modules.candidates.CandidateEntity;
import com.jaderhenryk.gestao_vagas.modules.candidates.repositories.CandidateRepository;

@Service
public class CreateCandidateUseCase {

    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CreateCandidateUseCase(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }
    
    public CandidateEntity execute(CandidateEntity candidate) {
        this.candidateRepository.findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail()).ifPresent(user -> {
            throw new UserAlreadyExistsException();
        });
        String password = passwordEncoder.encode(candidate.getPassword());
        candidate.setPassword(password);
        return this.candidateRepository.save(candidate);
    }
}
