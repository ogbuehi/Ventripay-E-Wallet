package com.example.ogbuehi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Login request
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @Email(message = "email.acceptable")
    @Size(min = 3, max = 20, message = "{username.size}")
    @NotBlank
    private String username;

    @Size(min = 6, max = 100, message = "{password.size}")
    @NotBlank
    private String password;
}
