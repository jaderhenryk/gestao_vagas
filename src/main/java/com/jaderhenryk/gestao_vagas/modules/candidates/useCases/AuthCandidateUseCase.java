package com.jaderhenryk.gestao_vagas.modules.candidates.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jaderhenryk.gestao_vagas.modules.candidates.CandidateEntity;
import com.jaderhenryk.gestao_vagas.modules.candidates.CandidateRepository;
import com.jaderhenryk.gestao_vagas.modules.candidates.dto.AuthCandidateRequestDTO;
import com.jaderhenryk.gestao_vagas.modules.candidates.dto.AuthCandidateResponseDTO;

@Service
public class AuthCandidateUseCase {

    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidateUseCase(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Value("${security.token.secret.candidate}")
    private String secretKey;
    
    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO) throws AuthenticationException {
        CandidateEntity candidate = this.candidateRepository.findByUsername(authCandidateRequestDTO.username())
            .orElseThrow(() -> {
                throw new UsernameNotFoundException("Username/password incorrect");
            });
        
        boolean passwordMatches = passwordEncoder.matches(authCandidateRequestDTO.password(), candidate.getPassword());
        if (!passwordMatches) {
            throw new AuthenticationException();
        }
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        Instant expiresIn = Instant.now().plus(Duration.ofMinutes(10));
        String token = JWT.create()
            .withIssuer("javagas")
            .withSubject(candidate.getId().toString())
            .withClaim("roles", Arrays.asList("CANDIDATE"))
            .withExpiresAt(expiresIn)
            .sign(algorithm);

        AuthCandidateResponseDTO authCandidateResponseDTO = AuthCandidateResponseDTO.builder()
            .access_token(token)
            .expires_in(expiresIn.toEpochMilli())
            .build();
        
        return authCandidateResponseDTO;
    }
}
