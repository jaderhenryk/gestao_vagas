package com.jaderhenryk.gestao_vagas.modules.candidates.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileCandidateResponseDTO {
    
    @Schema(example = "Java Developer")
    private String description;
    @Schema(example = "johndoe")
    private String username;
    @Schema(example = "John Doe")
    private String name;   
    @Schema(example = "johndow@email.com")
    private String email; 
    private UUID id;
}
