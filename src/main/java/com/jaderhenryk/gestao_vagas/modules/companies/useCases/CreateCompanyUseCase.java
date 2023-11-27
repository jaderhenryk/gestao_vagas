package com.jaderhenryk.gestao_vagas.modules.companies.useCases;

import org.springframework.stereotype.Service;

import com.jaderhenryk.gestao_vagas.exceptions.UserAlreadyExistsException;
import com.jaderhenryk.gestao_vagas.modules.companies.CompanyEntity;
import com.jaderhenryk.gestao_vagas.modules.companies.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {

    private CompanyRepository companyRepository;

    public CreateCompanyUseCase(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyEntity execute(CompanyEntity companyEntity) {
        this.companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
            .ifPresent(user -> {
                throw new UserAlreadyExistsException();
            });

        return this.companyRepository.save(companyEntity);
    }
}
