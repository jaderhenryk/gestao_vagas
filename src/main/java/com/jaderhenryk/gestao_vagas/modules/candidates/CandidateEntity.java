package com.jaderhenryk.gestao_vagas.modules.candidates;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "candidate")
public class CandidateEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Schema(example = "John Doe", requiredMode = RequiredMode.REQUIRED)
    private String name;

    @Pattern(regexp = "\\S+", message = "The field [username] should not contain white spaces.")
    @Schema(example = "johndoe", requiredMode = RequiredMode.REQUIRED)
    private String username;

    @Email(message = "The field [email] must contain a valid email.")
    @Schema(example = "johndoe@email.com", requiredMode = RequiredMode.REQUIRED)
    private String email;

    @Length(min = 10, max = 100, message = "The password must contain between 10 and 100 characters.")
    @Schema(example = "password@1234", minLength = 10, maxLength = 100, requiredMode = RequiredMode.REQUIRED)
    private String password;

    @Schema(example = "Java Developer", requiredMode = RequiredMode.REQUIRED)
    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
