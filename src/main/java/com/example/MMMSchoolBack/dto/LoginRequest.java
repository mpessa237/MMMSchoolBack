package com.example.MMMSchoolBack.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = "email is mandatory")
    @Email(message = "Email not well formatted")
    private String email;
    @NotBlank(message = "password is mandatory")
    @Length(min = 8, message = "Password must contain at least 8 characters.")
    private String password;
}
