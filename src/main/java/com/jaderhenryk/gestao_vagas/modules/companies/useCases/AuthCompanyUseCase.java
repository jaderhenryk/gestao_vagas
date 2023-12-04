package com.jaderhenryk.gestao_vagas.modules.companies.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jaderhenryk.gestao_vagas.modules.companies.CompanyEntity;
import com.jaderhenryk.gestao_vagas.modules.companies.dto.AuthCompanyDTO;
import com.jaderhenryk.gestao_vagas.modules.companies.dto.AuthCompanyResponseDTO;
import com.jaderhenryk.gestao_vagas.modules.companies.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {

    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${security.token.secret}")
    private String secretKey;

    public AuthCompanyUseCase(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    
    public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        CompanyEntity company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(() -> {
            throw new UsernameNotFoundException("Company not found.");
        });
        boolean passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());
        if (!passwordMatches) {
            throw new AuthenticationException();
        }
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        Instant expiresIn = Instant.now().plus(Duration.ofHours(2));
        String token = JWT.create().withIssuer("javagas")
            .withExpiresAt(expiresIn)
            .withSubject(company.getId().toString())
            .withClaim("roles", Arrays.asList("COMPANY"))
            .sign(algorithm);

        AuthCompanyResponseDTO authResponseDto = AuthCompanyResponseDTO.builder()
            .access_token(token)
            .expires_in(expiresIn.toEpochMilli())
            .build();

        return authResponseDto;
    }
}
