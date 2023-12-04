package com.jaderhenryk.gestao_vagas.modules.companies.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaderhenryk.gestao_vagas.modules.companies.CompanyEntity;
import com.jaderhenryk.gestao_vagas.modules.companies.JobEntity;
import com.jaderhenryk.gestao_vagas.modules.companies.dto.CreateJobDto;
import com.jaderhenryk.gestao_vagas.modules.companies.useCases.CreateCompanyUseCase;
import com.jaderhenryk.gestao_vagas.modules.companies.useCases.CreateJobUseCase;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {
    
    @Autowired
    private CreateCompanyUseCase createCompanyUseCase;

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity companyEntity) {
        try {
            var result = this.createCompanyUseCase.execute(companyEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/job")
    @PreAuthorize("hasRole('COMPANY')")
    public JobEntity create(
        @Valid @RequestBody CreateJobDto createJobDto,
        HttpServletRequest request
    ) {
        var companyId = request.getAttribute("company_id");
        JobEntity jobEntity = JobEntity.builder()
            .companyId(UUID.fromString(companyId.toString()))
            .benefits(createJobDto.getBenefits())
            .description(createJobDto.getDescription())
            .level(createJobDto.getLevel())
            .build();

        return this.createJobUseCase.execute(jobEntity);
    }
}
