package com.jaderhenryk.gestao_vagas.modules.candidates.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaderhenryk.gestao_vagas.modules.candidates.CandidateEntity;
import com.jaderhenryk.gestao_vagas.modules.candidates.dto.ProfileCandidateResponseDTO;
import com.jaderhenryk.gestao_vagas.modules.candidates.useCases.CreateCandidateUseCase;
import com.jaderhenryk.gestao_vagas.modules.candidates.useCases.ProfileCandidateUseCase;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;
    
    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidate) {
        try {
            var result = this.createCandidateUseCase.execute(candidate);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> getProfile(HttpServletRequest request) {
        var candidateId = request.getAttribute("candidate_id");
        try {
            ProfileCandidateResponseDTO profile = this.profileCandidateUseCase.execute(UUID.fromString(candidateId.toString()));
            return ResponseEntity.ok().body(profile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
