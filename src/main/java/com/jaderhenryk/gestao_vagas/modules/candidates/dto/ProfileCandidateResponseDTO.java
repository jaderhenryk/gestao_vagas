package com.jaderhenryk.gestao_vagas.modules.candidates.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileCandidateResponseDTO {
    
    private String description;
    private String username;
    private String name;   
    private String email; 
    private UUID id;
}
