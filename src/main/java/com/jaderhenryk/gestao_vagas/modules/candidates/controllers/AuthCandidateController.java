package com.jaderhenryk.gestao_vagas.modules.candidates.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaderhenryk.gestao_vagas.modules.candidates.dto.AuthCandidateRequestDTO;
import com.jaderhenryk.gestao_vagas.modules.candidates.dto.AuthCandidateResponseDTO;
import com.jaderhenryk.gestao_vagas.modules.candidates.useCases.AuthCandidateUseCase;

@RestController
@RequestMapping("/auth")
public class AuthCandidateController {
    
    private AuthCandidateUseCase authCandidateUseCase;

    public AuthCandidateController(AuthCandidateUseCase authCandidateUseCase) {
        this.authCandidateUseCase = authCandidateUseCase;
    }

    @PostMapping("/candidate")
    public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDTO authCandidateRequestDTO) {
        try {
            AuthCandidateResponseDTO token = authCandidateUseCase.execute(authCandidateRequestDTO);
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
