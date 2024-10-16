package com.devchangetheworld.ewebsite.dto.User;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {

    @NotBlank(message = "Invalid credentials")
    private String email;

    @NotBlank(message = "Invalid credentials")
    private String password;
}
