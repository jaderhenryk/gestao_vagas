package com.jaderhenryk.gestao_vagas.modules.companies.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthCompanyDTO {
    
    private String password;
    private String username;
}
