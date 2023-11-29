package com.jaderhenryk.gestao_vagas.modules.companies.dto;

import lombok.Data;

@Data
public class CreateJobDto {
    
    private String benefits;
    private String description;
    private String level;
}
