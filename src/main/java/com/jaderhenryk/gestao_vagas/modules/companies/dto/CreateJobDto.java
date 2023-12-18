package com.jaderhenryk.gestao_vagas.modules.companies.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateJobDto {
    
    @Schema(example = "GYMPass, health insurance.", requiredMode = RequiredMode.REQUIRED)
    private String benefits;
    @Schema(example = "Junior Development job opportunity.", requiredMode = RequiredMode.REQUIRED)
    private String description;
    @Schema(example = "Junior", requiredMode = RequiredMode.REQUIRED)
    private String level;
}
